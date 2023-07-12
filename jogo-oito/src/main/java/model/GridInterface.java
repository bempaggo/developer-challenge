package model;

import java.util.List;

public interface GridInterface {

    Integer getGridWidth();

    List<Integer> getGridData();

    Integer getEmptySlotIndex();

    List<Integer> getGameIsCompleteGridPattern();

    void reset();

}