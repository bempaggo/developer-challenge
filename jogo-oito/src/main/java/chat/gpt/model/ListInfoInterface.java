package chat.gpt.model;

import java.util.List;

public interface ListInfoInterface {

    int getGridSize();

    int getGridWidth();

    List<Integer> getGridData();

    int getEmptySlotIndex();

}