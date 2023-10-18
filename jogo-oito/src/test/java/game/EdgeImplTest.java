package game;

import service.CellImpl;
import service.EdgeImpl;
import util.Keyboard;
import service.interfaces.Edge;
import service.interfaces.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeImplTest {

    @Test
    public void testGetKey() {
        Edge edge = new EdgeImpl(Keyboard.RIGHT, null);
        assertEquals(Keyboard.RIGHT, edge.getKey());
    }

    @Test
    public void testGetCell() {
        Cell cell = new CellImpl(1);
        Edge edge = new EdgeImpl(Keyboard.RIGHT, cell);
        assertEquals(cell, edge.getCell());
    }

    @Test
    public void testEquals() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        Edge edge1 = new EdgeImpl(Keyboard.RIGHT, cell2);
        Edge edge2 = new EdgeImpl(Keyboard.LEFT, cell1);

        assertEquals(edge1.getCell().click(Keyboard.RIGHT), cell2);
        assertEquals(edge2.getCell().click(Keyboard.LEFT), cell1);

    }

}
