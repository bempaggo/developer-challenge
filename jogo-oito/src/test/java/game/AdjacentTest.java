package game;

import interfaces.Edge;
import interfaces.Vertex;
import model.Adjacent;
import model.Cell;
import enums.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacentTest {

    @Test
    void testGetKey() {
        Edge edge = Adjacent.of(Keyboard.RIGHT, null);
        assertEquals(Keyboard.RIGHT, edge.getKey());
    }

    @Test
    void testGetCell() {
        Vertex cell = Cell.of(1);
        Edge edge = Adjacent.of(Keyboard.RIGHT, cell);
        assertEquals(cell, edge.getCell());
    }

    @Test
    void testEquals() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        Edge edge1 = Adjacent.of(Keyboard.RIGHT, cell2);
        Edge edge2 = Adjacent.of(Keyboard.LEFT, cell1);

        assertEquals(edge1.getCell().click(Keyboard.RIGHT), cell2);
        assertEquals(edge2.getCell().click(Keyboard.LEFT), cell1);
    }

    @Test
    void testOf() {
        Vertex cell = Cell.of(1);
        Edge edge = Adjacent.of(Keyboard.LEFT, cell);

        assertEquals(edge.getCell(), cell);
        assertEquals(edge.getKey(), Keyboard.LEFT);
    }

    @Test
    void testCellValueIsEqualTrue() {
        Vertex cell = Cell.of(1);
        Edge edge = Adjacent.of(Keyboard.LEFT, cell);

        assertTrue(edge.cellValueIsEqual(1));
    }

    @Test
    void testCellValueIsEqualFalse() {
        Vertex cell = Cell.of(2);
        Edge edge = Adjacent.of(Keyboard.RIGHT, cell);

        assertFalse(edge.cellValueIsEqual(1));
    }

}
