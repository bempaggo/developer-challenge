package model;

import interfaces.Edge;
import interfaces.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO: i can only implement the proper asserts after i make cells subjects of its nodes
class CellTest {

    private Vertex cell;
    Random random = new Random();

    @BeforeEach
    void setUp() {
        Cell.content = 1;
        cell = new Cell();
        cell.setValue(random.nextInt());
    }

    @Test
    void valueToTextTest() {
        //chama o método que será testado, valor da cell é setado no @BeforeEach
        String text = cell.valueToText();

        assertNotNull(cell.valueToText());
        assertEquals(text, cell.valueToText());
    }
    @Test
    void performSwapByKeyWhenAdjacentPresent() {
        //testing for all values on enum Keyboard
        for (Keyboard key : Keyboard.values()) {
            //mocking an adjacent and a AdjacentCell, and adding it to cell.adjacents
            Vertex adjacentCell = new Cell();
            adjacentCell.setValue(random.nextInt());
            Adjacent adjacent = mock(Adjacent.class);
            when(adjacent.cell()).thenReturn(adjacentCell);
            when(adjacent.key()).thenReturn(key);
            cell.addAdjacents(adjacent);
            // copying the cells for asserts comparing
            Vertex adjacentCellClone = adjacentCell.clone();

            cell.performSwap(key);

            assertEquals(cell, adjacentCellClone);
        }
    }

    @Test
    void performSwapByKeyWhenAdjacentNull() {
        for (Keyboard key : Keyboard.values()) {
            Adjacent mockAdjacent = mock(Adjacent.class);
            when(mockAdjacent.cell()).thenReturn(null);
            when(mockAdjacent.key()).thenReturn(null);

            cell.performSwap(key);

            assertEquals(cell.getValue(), cell.getValue());
        }
    }


    @RepeatedTest(5)
    void performSwapByValueWhenAdjacentPresent() {
        Vertex adjacentCell = new Cell();
        adjacentCell.setValue(random.nextInt());
        Adjacent adjacent = mock(Adjacent.class);
        when(adjacent.cell()).thenReturn(adjacentCell);
        cell.addAdjacents(adjacent);

        // copying the cells for asserts comparing
        Vertex adjacentCellClone = adjacentCell.clone();

        cell.performSwap(adjacentCell.getValue());

        assertEquals(cell, adjacentCellClone);
    }


    @RepeatedTest(5)
    void swapByCellValueWhenCellNotPresentTest() {
        Adjacent mockAdjacent = mock(Adjacent.class);
        when(mockAdjacent.cell()).thenReturn(null);

        cell.performSwap(random.nextInt());

        assertEquals(cell.getValue(), cell.getValue());
    }

    @Test
    void addAdjacentsTest() {
        Edge mockAdjacent = mock(Adjacent.class);
        cell.addAdjacents(mockAdjacent);

        List<Edge> underTestAdjacents = cell.getAdjacents();

        assertNotNull(underTestAdjacents);
        assertTrue(underTestAdjacents.contains(mockAdjacent));
    }


}