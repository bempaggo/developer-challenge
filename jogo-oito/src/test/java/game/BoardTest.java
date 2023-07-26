/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author allen
 */

import enums.Keyboard;
import interfaces.Edge;
import interfaces.Vertex;
import model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.feedback();
    }

    @Test
    void testGetCells() {
        List<Vertex> cells = board.getCells();

        assertNotNull(cells);
        assertEquals(9, cells.size());
    }

    @Test
    void testDefineCellRelationships() {
        Vertex cell1 = board.getCells().get(1);
        Vertex cell2 = board.getCells().get(2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    void testCellCreateHorizontalAdjacent() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        cell1.createHorizontalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    void testCellCreateVerticalAdjacent() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        cell1.createVerticalAdjacent(cell2);

        Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.UP);
        Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.DOWN);

        assertEquals(cell2, adjacent1.getCell());
        assertEquals(cell1, adjacent2.getCell());
    }

    @Test
    void testValueToText() {
        Vertex cell = Cell.of(0);
        assertEquals("", cell.valueToText());

        cell.setValue(5);
        assertEquals("5", cell.valueToText());
    }

    @Test
    void testGetAdjacentByKeyCode() {
        Vertex cell1 = Cell.of(1);
        Vertex cell2 = Cell.of(2);

        cell1.createHorizontalAdjacent(cell2);

        Edge adjacent = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
        assertEquals(cell2, adjacent.getCell());
    }

}
