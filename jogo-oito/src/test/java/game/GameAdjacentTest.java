package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import interfaces.GameEdge;
import interfaces.GameVertex;
import model.GameAdjacent;
import model.GameCell;
import model.GameKeyboard;

public class GameAdjacentTest {

    @Test
    public void testGetKey() {
        GameEdge edge = new GameAdjacent(GameKeyboard.RIGHT, null);
        assertEquals(GameKeyboard.RIGHT, edge.getKeyboard());
    }

    @Test
    public void testGetCell() {
        GameVertex cell = new GameCell(1);
        GameEdge edge = new GameAdjacent(GameKeyboard.RIGHT, cell);
        assertEquals(cell, edge.getCell());
    }

    @Test
    public void testEquals() {
        GameVertex cell1 = new GameCell(1);
        GameVertex cell2 = new GameCell(2);

        GameEdge edge1 = new GameAdjacent(GameKeyboard.RIGHT, cell2);
        GameEdge edge2 = new GameAdjacent(GameKeyboard.LEFT, cell1);

        assertEquals(edge1.getCell().processCellClick(GameKeyboard.RIGHT), cell2);
        assertEquals(edge2.getCell().processCellClick(GameKeyboard.LEFT), cell1);
    }
}

