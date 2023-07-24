package game;

import model.Cell;
import util.Board;
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
        board.showSolvedBoard();
    }

    @Test
    public void testSwap() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer cellValue = cell.getValue();

        board.moveCellByValue(cellValue);

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

        Vertex adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        Vertex adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);

        assertEquals(cell2, adjacent1);
        assertEquals(cell1, adjacent2);
    }

    @Test
    public void testClick() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer cellValue = cell.getValue();

        board.moveCellByValue(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testCheckGameOver() {
        Assertions.assertTrue(this.board.checkGameOver());
        board = new Board();
        board.setUpNewBoard();
        Assertions.assertFalse(board.checkGameOver());

    }

    @Test
    public void testCellCreatingHorizontalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Vertex adjacent1 = cell1.getAdjacentByKeyCode(model.Keyboard.LEFT);
        Vertex adjacent2 = cell2.getAdjacentByKeyCode(model.Keyboard.RIGHT);

        assertEquals(cell2, adjacent1);
        assertEquals(cell1, adjacent2);
    }

    @Test
    public void testCellCreatingVerticalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingVerticalAdjacent(cell2);

        Vertex adjacent1 = cell1.getAdjacentByKeyCode(model.Keyboard.UP);
        Vertex adjacent2 = cell2.getAdjacentByKeyCode(model.Keyboard.DOWN);

        assertEquals(cell2, adjacent1);
        assertEquals(cell1, adjacent2);
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Vertex adjacent = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        assertEquals(cell2, adjacent);
    }

    @Test
    public void testClickDown() {
        board.moveCellByKey(Keyboard.DOWN.getValue());
        Assertions.assertEquals(5, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        board.moveCellByKey(Keyboard.UP.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        board.moveCellByKey(Keyboard.RIGHT.getValue());
        Assertions.assertEquals(7, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        board.moveCellByKey(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testShuffleCells() {
        board.setUpNewBoard();
        List<Vertex> cells = board.getCells();
        assertFalse(board.checkGameOver());

        Integer sumBeforeShuffle = cells.stream().mapToInt(Vertex::getValue).sum();
        board.setUpNewBoard();
        Integer sumAfterShuffle = cells.stream().mapToInt(Vertex::getValue).sum();
        assertEquals(sumBeforeShuffle, sumAfterShuffle);
    }

    @Test
    public void testMoveCellOutOfBounds() {
        board.getCells().forEach(cell -> cell.setValue(board.getCells().indexOf(cell)));

        board.moveCellByKey(Keyboard.LEFT.getValue());
        assertEquals(board.getEmptyCell().getValue(), board.getCells().get(0).getValue());

        board.moveCellByKey(Keyboard.UP.getValue());
        assertEquals(board.getEmptyCell().getValue(), board.getCells().get(0).getValue());
    }

}
