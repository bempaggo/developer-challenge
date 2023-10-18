/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import service.CellImpl;
import service.GraphImpl;
import service.interfaces.Edge;
import service.interfaces.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import util.Keyboard;
import org.junit.jupiter.api.Assertions;

public class BoardTest {

    private GraphImpl graphImpl;

    @BeforeEach
    public void createVerticalAdjacent() {
        graphImpl = new GraphImpl();
        graphImpl.feedback();
    }

    @Test
    public void testSwap() {
        Cell emptyCell = graphImpl.getEmptyCell();
        Cell cell = graphImpl.getCells().get(7);
        Integer cellValue = cell.getValue();

        graphImpl.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testGetCells() {
        List<Cell> cells = graphImpl.getCells();

        assertNotNull(cells);
        assertEquals(9, cells.size());
    }

    @Test
    public void testDefineCellRelationships() {
        Cell cell1 = graphImpl.getCells().get(1);
        Cell cell2 = graphImpl.getCells().get(2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testClick() {
        Cell emptyCell = graphImpl.getEmptyCell();
        Cell cell = graphImpl.getCells().get(7);
        Integer cellValue = cell.getValue();

        graphImpl.click(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testCheckGameOver() {
        Assertions.assertTrue(this.graphImpl.checkGameOver());
        graphImpl = new GraphImpl();
        graphImpl.setter();
        Assertions.assertFalse(graphImpl.checkGameOver());

    }

    @Test
    public void testCellcreateHorizontalAdjacent() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        cell1.createHorizontalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testCellcreateVerticalAdjacent() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        cell1.createVerticalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.UP);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.DOWN);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    public void testValueToText() {
        Cell cell = new CellImpl(0);
        assertEquals("", cell.valueToText());

        cell.setValue(5);
        assertEquals("5", cell.valueToText());
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        cell1.createHorizontalAdjacent(cell2);

        Edge adjacent = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        assertEquals(cell2, adjacent.getCell());
    }
    
    @Test
    public void testClickDown() {
        graphImpl.swap(Keyboard.DOWN.getValue());
        Assertions.assertEquals(5, graphImpl.getCells().indexOf(graphImpl.getEmptyCell()));
    }

    @Test
    public void testClickUp() {
        graphImpl.swap(Keyboard.UP.getValue());
        Assertions.assertEquals(8, graphImpl.getCells().indexOf(graphImpl.getEmptyCell()));
    }

    @Test
    public void testClickRight() {
        graphImpl.swap(Keyboard.RIGHT.getValue());
        Assertions.assertEquals(7, graphImpl.getCells().indexOf(graphImpl.getEmptyCell()));
    }

    @Test
    public void testClickLeft() {
        graphImpl.swap(Keyboard.LEFT.getValue());
        Assertions.assertEquals(8, graphImpl.getCells().indexOf(graphImpl.getEmptyCell()));
    }

}
