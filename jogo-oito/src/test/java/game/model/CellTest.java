/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import game.enums.Keyboard;
import game.interfaces.Vertex;

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
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    public void testCreatingVerticalAdjacent() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);

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
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByKeyCode(Keyboard.LEFT).getCell());
    }

    @Test
    public void testClick() {
        Cell cell1 = new Cell(1);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    public void testSwapCells() {
        Cell cell1 = new Cell(0);
        Cell cell2 = new Cell(2);
        cell1.creatingHorizontalAdjacent(cell2);

        cell1.swapCells(cell2.getValue());

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    public void defineAdjacents() {
        Cell cell1 = new Cell(5);
        Cell cell2 = new Cell(10);
        Cell cell3 = new Cell(15);
        Cell cell4 = new Cell(20);

        cell1.creatingVerticalAdjacent(cell2);
        cell1.creatingVerticalAdjacent(cell3);
        cell1.creatingHorizontalAdjacent(cell4);

        List<Adjacent> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }
}
