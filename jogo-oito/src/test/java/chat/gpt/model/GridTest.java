package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chat.gpt.util.Constants.*;

public class GridTest {


    @Test
    public void testDefaultGridCreation() {
        Grid grid = new Grid();
        List<Integer> gridData = grid.getGrid();
        Assertions.assertEquals(GRID_AREA, gridData.size());
        Assertions.assertTrue(gridData.contains(0));
    }

}