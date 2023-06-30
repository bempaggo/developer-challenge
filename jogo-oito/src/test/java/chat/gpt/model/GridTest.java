package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chat.gpt.exception.GridDoesNotFeatStandardsException;

public class GridTest {

    @Test
    public void testConstructor() {
        Grid grid = new Grid();

        int[][] expected = {
            { ONE, TWO, THREE },
            { FOUR, FIVE, SIX },
            { SEVEN, EIGHT, EMPTY }
        };

        int[][] actual = grid.getGrid();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testConstrutorComMatrizValida() {
        int[][] matrizValida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };

        Grid tabuleiro = new Grid(matrizValida);

        int[][] actual = tabuleiro.getGrid();

        Assertions.assertArrayEquals(matrizValida, actual);
    }

    @Test
    public void testConstrutorComNumerosRepetidos() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 7, 0 }
        };

        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(matrizInvalida));
    }

    @Test
    public void testConstrutorComFileiraFaltando() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(matrizInvalida));
    }

    @Test
    public void testConstrutorComColunaFaltando() {
        int[][] matrizInvalida = {
                { 1, 2 },
                { 4, 5 },
                { 7, 8 }
        };

        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(matrizInvalida));
    }

    @Test
    public void testConstrutorComFileiraSobrando() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 },
                {}
        };

        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(matrizInvalida));
    }

    @Test
    public void testConstrutorComColunaSobrando() {
        int[][] matrizInvalida = {
                { 1, 2, 3, 0 },
                { 4, 5, 6, 0 },
                { 7, 8, 0, }
        };

        Assertions.assertThrows(GridDoesNotFeatStandardsException.class, () -> new Grid(matrizInvalida));
    }

    @Test
    public void testGetTabuleiro() {
        int[][] matriz = {
                { 1, 3, 2 },
                { 6, 5, 4 },
                { 7, 8, 0 }
        };

        Grid tabuleiro = new Grid(matriz);

        int[][] actual = tabuleiro.getGrid();

        Assertions.assertArrayEquals(matriz, actual);
    }

}
