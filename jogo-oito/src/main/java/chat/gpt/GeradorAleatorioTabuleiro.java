package chat.gpt;

import java.util.Random;

public class GeradorAleatorioTabuleiro {
    private int[][] tabuleiro;
    private Random random;

    public GeradorAleatorioTabuleiro() {
        tabuleiro = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        random = new Random();
    }

    public int[][] gerarNumeros() {
        int numMovimentos = 100;
        for (int i = 0; i < numMovimentos; i++) {
            int linha1 = random.nextInt(3);
            int coluna1 = random.nextInt(3);
            int linha2 = random.nextInt(3);
            int coluna2 = random.nextInt(3);

            int temp = tabuleiro[linha1][coluna1];
            tabuleiro[linha1][coluna1] = tabuleiro[linha2][coluna2];
            tabuleiro[linha2][coluna2] = temp;
        }

        return tabuleiro;
    }

}
