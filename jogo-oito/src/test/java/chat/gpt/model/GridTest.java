package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chat.gpt.exception.GridDoesNotFeatStandardsException;

import java.util.Arrays;
import java.util.List;

import static chat.gpt.util.Constants.*;

public class GridTest {

    @Test
    public void testValidGridCreation() {
        List<Integer> validGridData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EMPTY);
        Grid grid = new Grid(validGridData);
        Assertions.assertEquals(validGridData, grid.getGrid());
    }

    @Test
    public void testInvalidGridCreation_InvalidSize() {
        List<Integer> invalidGridData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(invalidGridData));
    }

    @Test
    public void testInvalidGridCreation_DuplicateElements() {
        List<Integer> invalidGridData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 8);
        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(invalidGridData));
    }

    @Test
    public void testDefaultGridCreation() {
        Grid grid = new Grid();
        List<Integer> gridData = grid.getGrid();
        Assertions.assertEquals(GRID_AREA, gridData.size());
        Assertions.assertTrue(gridData.contains(EMPTY));
    }

    @Test
    public void testGetEmptyIndex() {
        List<Integer> gridData = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, EMPTY);
        Grid grid = new Grid(gridData);
        Assertions.assertEquals(8, grid.getEmptyIndex());
    }
}