package factories;

import interfaces.Edge;
import interfaces.Vertex;
import model.Adjacent;
import model.Cell;
import model.Keyboard;
import model.Matrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameFactoryTest {

    private GameFactory factory;

    @BeforeEach
    void setUp() {
        Cell.content = 1;
        factory = new GameFactoryImpl();
    }

    @Test
    void createCellTest() {
        Vertex cell = factory.createCell();
        assertNotNull(cell);
        assertTrue(cell instanceof Cell);
    }

    @Test
    void createAdjacentUpTest() {
        Edge adjacent = factory.createAdjacentUp(mock(Cell.class));
        assertNotNull(adjacent);
        assertTrue(adjacent instanceof Adjacent);
        assertEquals(adjacent.key(), Keyboard.UP);
    }

    @Test
    void createAdjacentDownTest() {
        Edge adjacent = factory.createAdjacentDown(mock(Cell.class));
        assertNotNull(adjacent);
        assertTrue(adjacent instanceof Adjacent);
        assertEquals(adjacent.key(), Keyboard.DOWN);
    }

    @Test
    void createAdjacentLeftTest() {
        Edge adjacent = factory.createAdjacentLeft(mock(Cell.class));
        assertNotNull(adjacent);
        assertTrue(adjacent instanceof Adjacent);
        assertEquals(adjacent.key(), Keyboard.LEFT);
    }

    @Test
    void createAdjacentRightTest() {
        Edge adjacent = factory.createAdjacentRight(mock(Cell.class));
        assertNotNull(adjacent);
        assertTrue(adjacent instanceof Adjacent);
        assertEquals(adjacent.key(), Keyboard.RIGHT);
    }

    @Test
    void createMatrixTest() {
        Matrix matrix = factory.createMatrix();
        assertNotNull(matrix);
    }
}