package chat.gpt.model;

import java.util.List;

public interface GridInterface {

    int getGridSize();

    int getGridWidth();

    List<Integer> getGridData();

    int getEmptySlotIndex();

    List<Integer> getGameIsCompleteGridPattern();

    void reset();


}