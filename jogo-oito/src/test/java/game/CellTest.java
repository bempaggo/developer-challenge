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

        assertEquals("", cell1.valueToText());
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
    public void testClick() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    public void testSwapCells() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        cell1.swapCells(cell2.getValue());

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    public void testSwapCells_NotFound() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        Cell cell3 = new Cell(3);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell2.swapCells(cell3.getValue()));
    }

}
