package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Arrays;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.EmptyPositionNotFoundException;
import chat.gpt.exception.PressedKeyDoesNothingException;

public class Game {
    private Grid grid;

    public Game() {
        grid = new Grid();
    }

    public void move(int[] coordinates) {

        if(coordinates == null) throw new PressedKeyDoesNothingException();

        int rowCoordinate = coordinates[0];
        int columnCordinate = coordinates[1];

        int[] emptyPosition = findEmptyPosition();
        int emptyRow = emptyPosition[0];
        int emptyColumn = emptyPosition[1];

        int newRow = emptyRow + rowCoordinate;
        int newColumn = emptyColumn + columnCordinate;

        if (validPosition(newRow, newColumn)) {
            changePositions(emptyRow, emptyColumn, newRow, newColumn);
        } else throw new ImpossibleMoveException();
    }
    
    private int[] findEmptyPosition() {
        int[][] gridData = gridActualState();

        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (gridData[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }

        throw new EmptyPositionNotFoundException();
    }

    private boolean validPosition(int row, int column) {
        return (row >= 0 && row < GRID_WIDTH) &&
                (column >= 0 && column < GRID_LENGTH);
                
    }

    private void changePositions(int row1, int column1, int row2, int column2) {
        int[][] gridData = gridActualState();
        int temp = gridData[row1][column1];
        gridData[row1][column1] = gridData[row2][column2];
        gridData[row2][column2] = temp;
    }


    public boolean gameIsComplete() {
        return Arrays.deepEquals(gridActualState(), GAME_FINISHED);
    }

    public void restartGame() {
        grid = new Grid();
    }

    public int[][] gridActualState() {
        return grid.getGrid();
    }
}
