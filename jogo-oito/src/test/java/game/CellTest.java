package game;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import model.Cell;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void setValue() {
        Cell cell = new Cell(5);
        cell.setValue(10);
        assertEquals(10, cell.getValue());
    }

    @Test
    void defineAdjacents() {
        Cell cell1 = new Cell(5);
        Cell cell2 = new Cell(10);
        Cell cell3 = new Cell(15);
        Cell cell4 = new Cell(20);

        cell1.defineUp(cell2);
        cell1.defineDown(cell3);
        cell1.defineLeft(cell4);

        HashMap<String, Cell> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get("UP"));
        assertEquals(cell3, adjacents.get("DOWN"));
        assertEquals(cell4, adjacents.get("LEFT"));
        assertNull(adjacents.get("RIGHT"));
    }

    @Test
    void valueToText() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(10);

        assertEquals("", cell1.valueToText());
        assertEquals("10", cell2.valueToText());
    }

}
