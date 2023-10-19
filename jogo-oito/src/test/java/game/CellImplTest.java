/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import service.CellImpl;
import service.interfaces.Edge;
import service.interfaces.Cell;
import java.util.List;
import service.EdgeImpl;
import util.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellImplTest {

    @Test
    public void testSetValue() {
        Cell cell = new CellImpl(0);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
    }

    @Test
    public void testGetValue() {
        Cell cell = new CellImpl(10);
        assertEquals(10, cell.getValue());
    }

    @Test
    public void testcreateHorizontalAdjacent() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        cell1.createHorizontalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    public void testcreateVerticalAdjacent() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);

        cell1.createVerticalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    public void testValueToText() {
        Cell cell1 = new CellImpl(0);
        Cell cell2 = new CellImpl(5);

        assertEquals("", cell1.valueToText());
        assertEquals("5", cell2.valueToText());
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);
        cell1.createHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByKeyCode(Keyboard.LEFT).getCell());
    }

    @Test
    public void testClick() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);
        cell1.createHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    public void testSwapCells() {
        Cell cell1 = new CellImpl(0);
        Cell cell2 = new CellImpl(2);
        cell1.createHorizontalAdjacent(cell2);

        cell1.swapCells(cell2.getValue());

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    public void testAddAdjacents() {
        Cell cell1 = new CellImpl(1);
        Cell cell2 = new CellImpl(2);
        Edge edge = new EdgeImpl(Keyboard.RIGHT, cell2);

        cell1.addAdjacent(edge);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(1, adjacents.size());
        assertEquals(edge, adjacents.get(0));
    }

    @Test
    public void defineAdjacents() {
        Cell cell1 = new CellImpl(5);
        Cell cell2 = new CellImpl(10);
        Cell cell3 = new CellImpl(15);
        Cell cell4 = new CellImpl(20);

        cell1.createVerticalAdjacent(cell2);
        cell1.createVerticalAdjacent(cell3);
        cell1.createHorizontalAdjacent(cell4);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }
}
