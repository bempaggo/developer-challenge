package chat.gpt.model;

import java.util.Arrays;

import chat.gpt.exception.MatrizInvalidaException;
import static chat.gpt.view.Constantes.*;

public class Tabuleiro {

    private int[][] matriz;

    public Tabuleiro(int[][] dificuldadeMatriz) {
        validarMatriz(dificuldadeMatriz);
        this.matriz = dificuldadeMatriz;
    }

    public Tabuleiro() {
        this(DIFICULDADE_PADRAO);
    }

    public int[][] getTabuleiro() {
        return matriz;
    }

    private void validarMatriz(int[][] matriz) {
        if (!tamanhoValido(matriz) || !semRepeticoes(matriz)) {
            throw new MatrizInvalidaException("A matriz de dificuldade é inválida");
        }
    }

    private boolean tamanhoValido(int[][] matriz) {
        if (matriz.length != boardLength) {
            return false;
        }
        for (int[] row : matriz) {
            if (row.length != boardWidth) {
                return false;
            }
        }
        return true;
    }

    private boolean semRepeticoes(int[][] matriz) {
        return Arrays.stream(matriz)
                .flatMapToInt(Arrays::stream)
                .distinct()
                .count() == boardArea;
    }

}
