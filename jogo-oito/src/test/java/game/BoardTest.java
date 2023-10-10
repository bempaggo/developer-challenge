/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author allen
 */
import model.Cell;
import util.Board;
import interfaces.Edge;
import interfaces.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.Keyboard;
import org.junit.jupiter.api.Assertions;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.feedback();
    }

    @Test
    public void testSwap() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer cellValue = cell.getValue();

        board.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testGetCells() {
        List<Vertex> cells = board.getCells();

        assertNotNull(cells);
        assertEquals(9, cells.size());
    }

    @Test
    public void testDefineCellRelationships() {
        Vertex cell1 = board.getCells().get(1);
        Vertex cell2 = board.getCells().get(2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(model.Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(model.Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testClick() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer cellValue = cell.getValue();

        board.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testCheckGameOver() {
        Assertions.assertTrue(this.board.checkGameOver());
        board = new Board();
        board.setting();
        Assertions.assertFalse(board.checkGameOver());

    }

    @Test
    public void testCellCreatingHorizontalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(model.Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(model.Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testCellCreatingVerticalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingVerticalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(model.Keyboard.UP);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(model.Keyboard.DOWN);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testValueToText() {
        Vertex cell = new Cell(0);
        assertEquals("", cell.valueToText());

        cell.setValue(5);
        assertEquals("5", cell.valueToText());
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Edge adjacent = cell1.getAdjacentByKeyCode(model.Keyboard.LEFT);
        assertEquals(cell2, adjacent.getCell());
    }

    @Test
    public void testClickDown() {
        board.swap(Keyboard.DOWN.getValue());
        Assertions.assertEquals(5, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        board.swap(Keyboard.UP.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        board.swap(Keyboard.RIGHT.getValue());
        Assertions.assertEquals(7, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        board.swap(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

}
