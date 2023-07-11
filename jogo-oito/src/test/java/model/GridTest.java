package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(16, 4, true);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36})
    void testGetGridSize(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        assertEquals(gridSize, grid.getGridSize());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36})
    void testGetGridWidth(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        assertEquals((int) Math.sqrt(gridSize), grid.getGridWidth());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36})
    void testGetGridData(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        List<Integer> gridData = grid.getGridData();
        assertNotNull(gridData);
        assertEquals(gridSize, gridData.size());
        assertTrue(gridData.contains(0));

        Set<Integer> uniqueValues = new HashSet<>(gridData);
        assertEquals(gridSize, uniqueValues.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36}) // Exemplo de tamanhos de grid para testar
    void testGetEmptySlotIndex(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        int emptySlotIndex = grid.getEmptySlotIndex();
        assertTrue(emptySlotIndex >= 0 && emptySlotIndex < grid.getGridSize());
        assertEquals(0, grid.getGridData().get(emptySlotIndex));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36})
    void testGetGameIsCompleteGridPattern(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        List<Integer> gameIsCompleteGridPattern = grid.getGameIsCompleteGridPattern();
        assertNotNull(gameIsCompleteGridPattern);
        assertEquals(gridSize, gameIsCompleteGridPattern.size());
        assertTrue(gameIsCompleteGridPattern.contains(0));

        Set<Integer> uniqueValues = new HashSet<>(gameIsCompleteGridPattern);
        assertEquals(gridSize, uniqueValues.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9, 16, 25, 36})
    void testReset(int gridSize) {
        grid = new Grid(gridSize, (int) Math.sqrt(gridSize), true);
        grid.reset();

        List<Integer> newGridData = grid.getGridData();
        assertNotNull(newGridData);
        assertEquals(gridSize, newGridData.size());
        assertTrue(newGridData.contains(0));

        Set<Integer> uniqueValues = new HashSet<>(newGridData);
        assertEquals(gridSize, uniqueValues.size());
    }
}
