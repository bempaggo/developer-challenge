package game;

import interfaces.Edge;
import interfaces.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;
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
        Vertex cell1 = new Cell(5);
        Vertex cell2 = new Cell(10);
        Vertex cell3 = new Cell(15);
        Vertex cell4 = new Cell(20);

        cell1.creatingVerticalAdjacent(cell2);
        cell1.creatingVerticalAdjacent(cell3);
        cell1.creatingHorizontalAdjacent(cell4);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }

    @Test
    void valueToText() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(10);

        assertEquals("", cell1.valueToText());
        assertEquals("10", cell2.valueToText());
    }

}
