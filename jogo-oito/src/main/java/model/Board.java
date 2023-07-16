package model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board implements BoardInterface {

    private final List<Integer> boardData;
    private final List<Integer> gameIsCompleteGridPattern;
    private final Integer boardSize;
    private final Integer boardWidth;
    private final Boolean randomBoard;

    public Board(Integer boardSize, Integer boardWidth, Boolean randomGrid) {
        this.randomBoard = randomGrid;
        this.boardSize = boardSize;
        this.boardWidth = boardWidth;
        this.boardData = createDefaultBoardData(boardSize);
        this.gameIsCompleteGridPattern = List.copyOf(boardData);
        this.randomizeGridData(this.randomBoard);
    }

    @Override
    public Integer getBoardSize() {
        return boardSize;
    }

    @Override
    public Integer getBoardWidth() {
        return boardWidth;
    }

    @Override
    public List<Integer> getBoardData() {
        return this.boardData;
    }

    @Override
    public Integer getEmptySlotIndex() {
        return getBoardData().indexOf(0);
    }

    @Override
    public List<Integer> getGameIsCompleteBoardPattern() {
        return gameIsCompleteGridPattern;
    }

    @Override
    public void solution() {
        boardData.clear();
        boardData.addAll(getGameIsCompleteBoardPattern());
    }

    @Override
    public void reset() {
        boardData.clear();
        boardData.addAll(createDefaultBoardData(this.boardSize));
        randomizeGridData(this.randomBoard);
    }

    private List<Integer> createDefaultBoardData(int boardSize) {
        List<Integer> gridData = IntStream.range(1, boardSize)
                .boxed()
                .collect(Collectors.toList());
        gridData.add(0);
        return gridData;
    }

    private void randomizeGridData(Boolean randomBoard) {
        Optional.ofNullable(randomBoard)
                .filter(Boolean::booleanValue)
                .ifPresent(ignored -> Collections.shuffle(this.boardData));
    }

}