package game;

import model.Adjacent;
import model.Cell;
import model.Keyboard;
import interfaces.Edge;
import interfaces.Vertex;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdjacentTest {

    @Test
    public void testGetKey() {
        Edge edge = new Adjacent(Keyboard.RIGHT, null);
        assertEquals(Keyboard.RIGHT, edge.getKey());
    }

    @Test
    public void testGetCell() {
        Vertex cell = new Cell(1);
        Edge edge = new Adjacent(Keyboard.RIGHT, cell);
        assertEquals(cell, edge.getCell());
    }

    @Test
    public void testEquals() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        Edge edge = new Adjacent(Keyboard.RIGHT, cell2);
        Edge edge12 = new Adjacent(Keyboard.LEFT, cell1);

        assertEquals(edge.getCell().click(Keyboard.RIGHT), cell2);
        assertEquals(edge12.getCell().click(Keyboard.LEFT), cell1);

    }

}
