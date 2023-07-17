package model;

import java.util.List;

import util.BoardDataObserver;

public interface BoardInterface {

    Integer getBoardSize();

    Integer getBoardWidth();

    List<Integer> getBoardData();

    Integer getEmptySlotIndex();

    List<Integer> getGameIsCompleteBoardPattern();

    void reset();

    void solution();

    void swapElements(Integer index);

    void registerObserver(BoardDataObserver observer);
        
}