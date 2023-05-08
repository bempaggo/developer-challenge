package victor.managers;

import java.util.*;
import java.util.stream.IntStream;

import victor.entities.BoardPosition;

public class BoardManager {
    private final int boardSize;
    private final int[][] board;
    private final HashMap<String, BoardPosition> boardPositions = new HashMap<>();

    public BoardManager(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];

        resetBoard();
    }

    public BoardPosition getBoardPosition(String boardValue) {
        return this.boardPositions.get(boardValue);
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public int getBoardValue(int xPosition, int yPosition) {
        return this.board[xPosition][yPosition];
    }

    public BoardPosition getEmptyPositions() {
        return this.boardPositions.get("0");
    }

    public void setBoardPositions(Integer boardValue, BoardPosition boardPosition) {
        board[boardPosition.getXPosition()][boardPosition.getYPosition()] = boardValue;
        boardPositions.put(boardValue.toString(), boardPosition);
    }

    public void resetBoard() {
        ArrayList<Integer> defaultValuesList = new ArrayList<>();
        int totalBoardSize = this.boardSize * this.boardSize;

        IntStream.range(0, totalBoardSize).forEach(defaultValuesList::add);

        initializeBoardPositions(defaultValuesList);
    }

    private void initializeBoardPositions(ArrayList<Integer> defaultValues) {
        Random rand = new Random();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int randomChoice = rand.nextInt(defaultValues.size());
                int boardValue = defaultValues.remove(randomChoice);

                board[i][j] = boardValue;
                boardPositions.put(Integer.toString(boardValue), new BoardPosition(i, j));
            }
        }
    }

    private int safeSearchInBoard(int x, int y) {
        try {
            return this.getBoardValue(x, y);
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean hasEmptySpaceInSurround(int xPosition, int yPosition) {
        boolean hasEmptyAbove = safeSearchInBoard(xPosition, yPosition + 1) == 0;
        boolean hasEmptyBelow = safeSearchInBoard(xPosition, yPosition - 1) == 0;
        boolean hasEmptyLeft = safeSearchInBoard(xPosition - 1, yPosition) == 0;
        boolean hasEmptyRight = safeSearchInBoard(xPosition + 1, yPosition) == 0;

        return hasEmptyBelow || hasEmptyLeft || hasEmptyAbove || hasEmptyRight;
    }

    public boolean isOrdered() {
        int highestValue = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int currentValue = board[i][j];

                if (currentValue == 0 && (i != boardSize - 1 || j != boardSize - 1)) {
                    return false;
                }

                if (currentValue != 0 && currentValue > highestValue) {
                    highestValue = currentValue;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean changeButtonsPositions(int buttonXPosition, int buttonYPosition) {
        boolean hasEmptySpaceInSurround = this.hasEmptySpaceInSurround(buttonXPosition, buttonYPosition);

        if (hasEmptySpaceInSurround) {
            int value = this.getBoardValue(buttonXPosition, buttonYPosition);
            BoardPosition emptyPosition = this.getEmptyPositions();

            this.setBoardPositions(value, emptyPosition);
            this.setBoardPositions(0, new BoardPosition(buttonXPosition, buttonYPosition));
        }

        return hasEmptySpaceInSurround;
    }
}
