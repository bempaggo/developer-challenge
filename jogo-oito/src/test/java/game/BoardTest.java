package game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Board;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board(-1);
        board.loadCells();
        board.defineCellRelationships();
    }

    @Test
    public void testSwap() {
        int value = 6;
        board.swap(value);
        Assertions.assertEquals(value, board.getCells().get(board.getCells().size() - 1).getValue());
    }

    @Test
    public void testClickDown() {
        board.clickDown();
        Assertions.assertEquals(5, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        board.clickUp();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        board.clickRight();
        Assertions.assertEquals(7, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        board.clickLeft();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testBoardLimit() {
        board.clickLeft();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.clickLeft();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.clickLeft();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
        board.clickLeft();
        Assertions.assertEquals(8, board.getCells().indexOf(board.getEmptyCell()));
    }

    @Test
    public void testCheckGameOver() {
        Assertions.assertTrue(this.board.checkGameOver());
        board = new Board(123);
        board.loadCells();
        board.defineCellRelationships();
        Assertions.assertFalse(board.checkGameOver());

    }

}
