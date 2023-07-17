/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import interfaces.Edge;
import interfaces.Vertex;
import java.util.List;
import model.Adjacent;
import model.Cell;
import model.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest{

    @Test
    public void testSetValue() {
        Vertex cell = new Cell(0);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
    }

    @Test
    public void testGetValue() {
        Vertex cell = new Cell(10);
        assertEquals(10, cell.getValue());
    }

    @Test
    public void testCreatingHorizontalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    public void testCreatingVerticalAdjacent() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);

        cell1.creatingVerticalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    public void testValueToText() {
        Vertex cell1 = new Cell(0);
        Vertex cell2 = new Cell(5);

        assertEquals("", cell1.valueToText());
        assertEquals("5", cell2.valueToText());
    }

    @Test
    public void testGetAdjacentByKeyCode() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByKeyCode(Keyboard.LEFT).getCell());
    }

    @Test
    public void testClick() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    public void testSwapCells() {
        Vertex cell1 = new Cell(0);
        Vertex cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        cell1.swapCells(cell2.getValue());

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    public void testAddAdjacents() {
        Vertex cell1 = new Cell(1);
        Vertex cell2 = new Cell(2);
        Edge edge = new Adjacent(Keyboard.RIGHT, cell2);

        cell1.addAdjacents(edge);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(1, adjacents.size());
        assertEquals(edge, adjacents.get(0));
    }

    @Test
    public void defineAdjacents() {
        Vertex cell1 = new Cell(5);
        Vertex cell2 = new Cell(10);
        Vertex cell3 = new Cell(15);
        Vertex cell4 = new Cell(20);

        cell1.creatingVerticalAdjacent(cell2);
        cell1.creatingVerticalAdjacent(cell3);
        cell1.creatingHorizontalAdjacent(cell4);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }
}
