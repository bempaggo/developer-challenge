package chat.gpt.model;

import static chat.gpt.view.Constantes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chat.gpt.exception.MatrizInvalidaException;

public class TabuleiroTest {

    @Test
    public void testConstrutorPadrao() {
        Tabuleiro tabuleiro = new Tabuleiro();

        int[][] expected = {
                { UM, DOIS, TRES },
                { QUATRO, CINCO, SEIS },
                { SETE, OITO, VAZIO }
        };

        int[][] actual = tabuleiro.getTabuleiro();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testConstrutorComMatrizValida() {
        int[][] matrizValida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };

        Tabuleiro tabuleiro = new Tabuleiro(matrizValida);

        int[][] actual = tabuleiro.getTabuleiro();

        Assertions.assertArrayEquals(matrizValida, actual);
    }

    @Test
    public void testConstrutorComNumerosRepetidos() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 7, 0 }
        };

        Assertions.assertThrows(MatrizInvalidaException.class, () -> new Tabuleiro(matrizInvalida));
    }
    @Test
    public void testConstrutorComFileiraFaltando() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };

        Assertions.assertThrows(MatrizInvalidaException.class, () -> new Tabuleiro(matrizInvalida));
    }

    @Test
    public void testConstrutorComColunaFaltando() {
        int[][] matrizInvalida = {
                { 1, 2 },
                { 4, 5 },
                { 7, 8 }
        };

        Assertions.assertThrows(MatrizInvalidaException.class, () -> new Tabuleiro(matrizInvalida));
    }

    @Test
    public void testConstrutorComFileiraSobrando() {
        int[][] matrizInvalida = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 },
                { }
        };

        Assertions.assertThrows(MatrizInvalidaException.class, () -> new Tabuleiro(matrizInvalida));
    }
    @Test
    public void testConstrutorComColunaSobrando() {
        int[][] matrizInvalida = {
                { 1, 2, 3, 0 },
                { 4, 5, 6, 0 },
                { 7, 8, 0,  }
        };

        Assertions.assertThrows(MatrizInvalidaException.class, () -> new Tabuleiro(matrizInvalida));
    }

    @Test
    public void testGetTabuleiro() {
        int[][] matriz = {
                { 1, 3, 2 },
                { 6, 5, 4 },
                { 7, 8, 0 }
        };

        Tabuleiro tabuleiro = new Tabuleiro(matriz);

        int[][] actual = tabuleiro.getTabuleiro();

        Assertions.assertArrayEquals(matriz, actual);
    }

}
