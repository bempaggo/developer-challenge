package util;

import java.util.List;

public interface BoardDataObserver {
    void boardDataChanged(List<Integer> newBoardData);
}
