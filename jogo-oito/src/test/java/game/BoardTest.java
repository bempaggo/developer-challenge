package game;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author allen
 */
import java.util.Collections;
import model.Board;
import model.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board(3, 3, 123);
        board.fillMatrix();
    }

    @Test
    public void testGetMatrix() {
        List<List<Cell>> matrix = board.getMatrix();
        Assert.assertNotNull(matrix);
        Assert.assertEquals(3, matrix.size());
        Assert.assertEquals(3, matrix.get(0).size());
    }

    @Test
    public void testGetLength() {
        int length = board.getLength();
        Assert.assertEquals(9, length);
    }

    @Test
    public void testGetRowSize() {
        int rowSize = board.getRowSize();
        Assert.assertEquals(3, rowSize);
    }

    @Test
    public void testGetColumnSize() {
        int columnSize = board.getColumnSize();
        Assert.assertEquals(3, columnSize);
    }

    @Test
    public void testFindEmptyCell() {
        Cell emptyCell = board.findEmptyCell();
        Assert.assertNotNull(emptyCell);
        Assert.assertEquals(0, emptyCell.getValue().intValue());
    }

    @Test
    public void testFindCellByValue() {
        Cell cell = board.findCellByValue(5);
        Assert.assertNotNull(cell);
        Assert.assertEquals(5, cell.getValue().intValue());
    }

    @Test
    public void testPositionIsValid() {
        boolean valid = board.positionIsValid(0, 0);
        Assert.assertTrue(valid);

        valid = board.positionIsValid(2, 2);
        Assert.assertTrue(valid);

        valid = board.positionIsValid(3, 3);
        Assert.assertFalse(valid);

        valid = board.positionIsValid(-1, 0);
        Assert.assertFalse(valid);
    }

    @Test
    public void testGetCellByRowAndColumnIndex() {
        Cell cell = board.getCellByRowAndColumnIndex(1, 2);
        Assert.assertNotNull(cell);
        Assert.assertEquals(1, cell.getIndexRow());
        Assert.assertEquals(2, cell.getIndexColumn());
    }

    @Test
    public void testSwapValue() {
        Cell emptyCell = board.findEmptyCell();
        Cell exchangeCell = board.findCellByValue(5);

        int emptyCellValue = emptyCell.getValue();
        int exchangeCellValue = exchangeCell.getValue();

        board.swapValue(emptyCell, exchangeCell);

        Assert.assertEquals(exchangeCellValue, emptyCell.getValue().intValue());
        Assert.assertEquals(emptyCellValue, exchangeCell.getValue().intValue());
    }

    @Test
    public void testCheckGameOver() {
        boolean gameOver = board.checkGameOver();
        Assert.assertFalse(gameOver);
    }
}

