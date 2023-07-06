package game;

import interfaces.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;
import model.Keyboard;

public class BoardTest {

    private Graph board;

    @BeforeEach
    public void setup() {
        board = new Board(Boolean.TRUE);
        board.loadCells();
        board.defineCellRelationships();
    }

    @Test
    public void testSwap() {
        Integer value = 6;
        board.swap(value);
        Assertions.assertEquals(value, board.getCells().get(board.getCells().size() - 1).getValue());
    }

    @Test
    public void testClickDown() {
        board.click(Keyboard.DOWN.getValue());
        Assertions.assertEquals(5, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        board.click(Keyboard.UP.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        board.click(Keyboard.RIGHT.getValue());
        Assertions.assertEquals(7, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        board.click(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testBoardLimit() {
        board.click(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.click(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.click(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.click(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testCheckGameOver() {
        Assertions.assertTrue(this.board.checkGameOver());
        board = new Board(Boolean.FALSE);
        board.loadCells();
        board.defineCellRelationships();
        Assertions.assertFalse(board.checkGameOver());

    }

}
