/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.enums.Keyboard;
import game.interfaces.Edge;
import game.interfaces.Vertex;

public class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    public void setUp() {
        matrix = new Matrix();
        matrix.createCells(true);
    }

    @Test
    public void testSwap() {
        Vertex emptyCell = matrix.getEmptyCell();
        Vertex cell = matrix.getCells().get(7);
        Integer cellValue = cell.getValue();

        matrix.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testGetCells() {
        List<Vertex> cells = matrix.getCells();

        assertNotNull(cells);
        assertEquals(9, cells.size());
    }

    @Test
    public void testDefineCellRelationships() {
        Vertex cell1 = matrix.getCells().get(1);
        Vertex cell2 = matrix.getCells().get(2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(game.enums.Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(game.enums.Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testClick() {
        Vertex emptyCell = matrix.getEmptyCell();
        Vertex cell = matrix.getCells().get(7);
        Integer cellValue = cell.getValue();

        matrix.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testcheckvictory() {
        Assertions.assertTrue(this.matrix.checkVictory());
        matrix = new Matrix();
        matrix.createCells(false);
        Assertions.assertFalse(matrix.checkVictory());
    }

    @Test
    public void testCellCreatingHorizontalAdjacent() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(game.enums.Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(game.enums.Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testCellCreatingVerticalAdjacent() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingVerticalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(game.enums.Keyboard.UP);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(game.enums.Keyboard.DOWN);

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
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        Edge adjacent = cell1.getAdjacentByKeyCode(game.enums.Keyboard.LEFT);
        assertEquals(cell2, adjacent.getCell());
    }
    
    @Test
    public void testClickDown() {
        matrix.swap(Keyboard.DOWN.getValue());
        Assertions.assertEquals(5, matrix.getCells().indexOf(matrix.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        matrix.swap(Keyboard.UP.getValue());
        Assertions.assertEquals(8, matrix.getCells().indexOf(matrix.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        matrix.swap(Keyboard.RIGHT.getValue());
        Assertions.assertEquals(7, matrix.getCells().indexOf(matrix.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        matrix.swap(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, matrix.getCells().indexOf(matrix.getEmptyCell()));
    }

}
