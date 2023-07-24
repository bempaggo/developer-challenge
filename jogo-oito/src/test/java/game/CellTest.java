package game;

import model.Cell;
import model.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    @Test
    public void testSetValue() {
        Cell cell = new Cell(0);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
    }

    @Test
    public void testGetValue() {
        Cell cell = new Cell(10);
        assertEquals(10, cell.getValue());
    }

    @Test
    public void testCreatingHorizontalAdjacent() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(cell2, cell1.getAdjacents().get(Keyboard.LEFT));
        assertEquals(1, cell2.getAdjacents().size());
        assertEquals(cell1, cell2.getAdjacents().get(Keyboard.RIGHT));
    }

    @Test
    public void testCreatingVerticalAdjacent() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingVerticalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(cell2, cell1.getAdjacents().get(Keyboard.UP));
        assertEquals(1, cell2.getAdjacents().size());
        assertEquals(cell1, cell2.getAdjacents().get(Keyboard.DOWN));
    }

    @Test
    public void testValueToText() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(5);

        assertEquals("0", cell1.valueToText());
        assertEquals("5", cell2.valueToText());
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByKeyCode(Keyboard.LEFT));
    }

    @Test
    public void testGetAdjacentByValue() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByValue(2));
    }

    @Test
    public void testSwapCells() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        cell1.swapCells(cell2);

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    public void testNullValue() {
        Cell cell = new Cell(null);
        assertNull(cell.getValue());
    }

    @Test
    public void testNonExistentAdjacent() {
        Cell cell1 = new Cell(1);

        // célula retorna ela mesma se não achar adjacente, evita null checks no código
        assertEquals(cell1, cell1.getAdjacentByKeyCode(Keyboard.LEFT));
        assertEquals(cell1, cell1.getAdjacentByValue(2));
    }

    @Test
    public void testEqualsMethod() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(1);
        Cell cell3 = new Cell(3);

        assertTrue(cell1.equals(cell2));
        assertFalse(cell1.equals(cell3));
    }

}
