/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import interfaces.Edge;
import interfaces.Vertex;
import model.Adjacent;
import model.Cell;
import enums.Keyboard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest{

    @Test
    void testSetValue() {
        Vertex cell = Cell.of(0);
        cell.setValue(5);
        assertEquals(5, cell.getValue());
    }

    @Test
    void testGetValue() {
        Vertex cell = Cell.of(10);
        assertEquals(10, cell.getValue());
    }

    @Test
    void testCreatingHorizontalAdjacent() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    void testCreatingVerticalAdjacent() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        cell1.creatingVerticalAdjacent(cell2);

        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    void testValueToText() {
        Vertex cell1 = Cell.of(0);
        Vertex cell2 = Cell.of(5);

        assertEquals("", cell1.valueToText());
        assertEquals("5", cell2.valueToText());
    }

    @Test
    void testGetAdjacentByKeyCode() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.getAdjacentByKeyCode(Keyboard.LEFT).getCell());
    }

    @Test
    void testClick() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    void testSwapCells() {
        Vertex cell1 = Cell.of(0);
        Vertex cell2 = Cell.of(2);
        cell1.creatingHorizontalAdjacent(cell2);

        cell1.swapCells(cell2.getValue());

        assertEquals(2, cell1.getValue());
        assertEquals(0, cell2.getValue());
    }

    @Test
    void testAddAdjacents() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);
        Edge edge = Adjacent.of(Keyboard.RIGHT, cell2);

        cell1.addAdjacents(edge);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(1, adjacents.size());
        assertEquals(edge, adjacents.get(0));
    }

    @Test
    void testCreateAdjacents() {
        Vertex cell1 = Cell.of(5);
        Vertex cell2 = Cell.of(10);
        Vertex cell3 = Cell.of(15);
        Vertex cell4 = Cell.of(20);

        cell1.creatingVerticalAdjacent(cell2);
        cell1.creatingVerticalAdjacent(cell3);
        cell1.creatingHorizontalAdjacent(cell4);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }

    @Test
    void testOf() {
        Vertex cell1 = Cell.of(5);
        Vertex cell2 = Cell.of(10);
        Vertex cell3 = Cell.of(15);
        Vertex cell4 = Cell.of(20);

        assertEquals(cell1.getValue(), 5);
        assertEquals(cell2.getValue(), 10);
        assertEquals(cell3.getValue(), 15);
        assertEquals(cell4.getValue(), 20);
    }

    @Test
    void testStaticValueToText() {
        Vertex cell1 = Cell.of(5);
        Vertex cell2 = Cell.of(10);
        Vertex cell3 = Cell.of(15);
        Vertex cell4 = Cell.of(20);

        List<Vertex> cells = List.of(cell1, cell2, cell3, cell4);

        assertEquals(Cell.valueToText(cells, 0), "5");
        assertEquals(Cell.valueToText(cells, 1), "10");
        assertEquals(Cell.valueToText(cells, 2), "15");
        assertEquals(Cell.valueToText(cells, 3), "20");
    }

}
