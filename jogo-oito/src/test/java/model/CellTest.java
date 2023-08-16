package model;

import interfaces.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CellTest {

    private Cell cell;
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
    void findAdjacentByKeycodeAndCallSwapWhenAdjacentPresentTest() {
        //testa para todos os valores no enum Keyboard
        for (Keyboard key : Keyboard.values()) {
            //cria uma célula adjacente e mocka uma adjacent que retorna ela e a key, add na célula
            Cell adjacentCell = new Cell();
            adjacentCell.setValue(random.nextInt());
            Adjacent adjacent = mock(Adjacent.class);
            when(adjacent.cell()).thenReturn(adjacentCell);
            when(adjacent.key()).thenReturn(key);
            cell.addAdjacents(adjacent);

            //chama o método que será testado
            Cell result = (Cell) cell.findAdjacentByKeycodeAndCallSwap(key);

            assertEquals(adjacent.cell().getValue(), result.getValue());
            assertNotNull(result);
            assertNotSame(cell, result);
        }
    }

    @Test
    void findAdjacentByKeycodeAndCallSwapForAllKeyValues() {
        //testa para todos os valores no enum Keyboard
        for (Keyboard key : Keyboard.values()) {
            //mocka uma Adjacent nula
            Adjacent mockAdjacent = mock(Adjacent.class);
            when(mockAdjacent.cell()).thenReturn(null);
            when(mockAdjacent.key()).thenReturn(null);

            //chama o método que será testado
            Cell result = (Cell) cell.findAdjacentByKeycodeAndCallSwap(key);

            assertEquals(cell.getValue(), result.getValue());
            assertNotNull(result);
            assertSame(cell, result);
        }
    }

    @RepeatedTest(5)
    void swapByCellValueWhenCellPresentTest() {
        //cria uma célula adjacente, mocka um Adjacent pra retornar ela e adiciona as adjacências de cell
        Cell adjacentCell = new Cell();
        adjacentCell.setValue(random.nextInt());
        Adjacent adjacent = mock(Adjacent.class);
        when(adjacent.cell()).thenReturn(adjacentCell);
        cell.addAdjacents(adjacent);

        //chama o método que será testado e armazena numa Cell para usar nos asserts
        Cell result = (Cell) cell.findAdjacentByCellValueAndCallSwap(adjacentCell.getValue());

        assertEquals(adjacent.cell().getValue(), result.getValue());
        assertNotNull(result);
        assertNotSame(cell, result);
    }

    @RepeatedTest(5)
    void swapByCellValueWhenCellNotPresentTest() {
        // Mocka um Adjacent para retornar null
        Adjacent mockAdjacent = mock(Adjacent.class);
        when(mockAdjacent.cell()).thenReturn(null);

        // Chama o método que será testado
        Cell result = (Cell) cell.findAdjacentByCellValueAndCallSwap(random.nextInt());

        // Verifica que o valor da célula não foi alterado e que o método retornou a própria célula
        assertEquals(cell.getValue(), result.getValue());
        assertSame(cell, result);
    }

    @Test
    void addAdjacentsTest() {
        //mocka uma Adjacent e adiciona à célula, chama o método que é testado
        Edge mockAdjacent = mock(Adjacent.class);
        cell.addAdjacents(mockAdjacent);

        //armazena as adjacents para os asserts
        List<Edge> underTestAdjacents = cell.getAdjacents();

        assertNotNull(underTestAdjacents);
        assertTrue(underTestAdjacents.contains(mockAdjacent));
    }

}