package game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.enums.Keyboard;

public class AdjacentTest {

    @Test
    public void testGetKey() {
        Adjacent edge = new Adjacent(Keyboard.RIGHT, null);
        assertEquals(Keyboard.RIGHT, edge.getKey());
    }

    @Test
    public void testGetCell() {
        Cell cell = new Cell(1);
        Adjacent edge = new Adjacent(Keyboard.RIGHT, cell);
        assertEquals(cell, edge.getCell());
    }

    @Test
    public void testEquals() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        Adjacent edge1 = new Adjacent(Keyboard.RIGHT, cell2);
        Adjacent edge2 = new Adjacent(Keyboard.LEFT, cell1);

        assertEquals(edge1.getCell().click(Keyboard.RIGHT), cell2);
        assertEquals(edge2.getCell().click(Keyboard.LEFT), cell1);
    }

}
