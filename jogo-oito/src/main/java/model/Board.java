package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.BoardDataObserver;

public class Board implements BoardInterface {

    private final List<Integer> boardData;
    private final List<Integer> gameIsCompleteGridPattern;
    private final Integer boardSize;
    private final Integer boardWidth;
    private List<BoardDataObserver> observers = new ArrayList<>();

    public Board(Integer boardSize, Integer boardWidth) {
        this.boardSize = boardSize;
        this.boardWidth = boardWidth;
        this.boardData = createDefaultBoardData(boardSize);
        this.gameIsCompleteGridPattern = List.copyOf(boardData);
        this.randomizeGridData();
        notifyObservers();
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

    public void registerObserver(BoardDataObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<Integer> getGameIsCompleteBoardPattern() {
        return gameIsCompleteGridPattern;
    }

    @Override
    public void solution() {
        boardData.clear();
        boardData.addAll(getGameIsCompleteBoardPattern());
        notifyObservers();
    }

    @Override
    public void reset() {
        boardData.clear();
        boardData.addAll(createDefaultBoardData(this.boardSize));
        randomizeGridData();
        notifyObservers();
    }

    @Override
    public void swapElements(Integer index) {
        Integer emptySlotIndex = getEmptySlotIndex();

        Integer temp = boardData.get(emptySlotIndex);
        boardData.set(emptySlotIndex, boardData.get(index));
        boardData.set(index, temp);
        notifyObservers();
    }

    private List<Integer> createDefaultBoardData(int boardSize) {
        List<Integer> gridData = IntStream.range(1, boardSize)
                .boxed()
                .collect(Collectors.toList());
        gridData.add(0);
        return gridData;
    }

    private void randomizeGridData() {
        Collections.shuffle(this.boardData);
    }

    private void notifyObservers() {
        for (BoardDataObserver observer : observers) {
            observer.boardDataChanged(boardData);
        }
    }

}