package chat.gpt.model;

public class JogoDosOito {
    private Tabuleiro tabuleiro;

    public JogoDosOito() {
        tabuleiro = new Tabuleiro();
    }

    public void mover(int linha, int coluna) {
        int[] posicaoVazia = encontrarPosicaoVazia();

        int linhaVazia = posicaoVazia[0];
        int colunaVazia = posicaoVazia[1];

        int novaLinha = linhaVazia + linha;
        int novaColuna = colunaVazia + coluna;

        if (!posicaoValida(novaLinha, novaColuna)) {
            return;
        }

        trocarPosicoes(linhaVazia, colunaVazia, novaLinha, novaColuna);
    }

    private int[] encontrarPosicaoVazia() {
        int[][] tabuleiroData = tabuleiro.getTabuleiro();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiroData[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }

        // Retorna uma posição inválida caso não encontre a posição vazia
        return new int[]{-1, -1};
    }

    private boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3;
    }

    private void trocarPosicoes(int linha1, int coluna1, int linha2, int coluna2) {
        int[][] tabuleiroData = tabuleiro.getTabuleiro();
        int temp = tabuleiroData[linha1][coluna1];
        tabuleiroData[linha1][coluna1] = tabuleiroData[linha2][coluna2];
        tabuleiroData[linha2][coluna2] = temp;
    }

    public boolean jogoConcluido() {
        int count = 1;
        int[][] tabuleiroData = tabuleiro.getTabuleiro();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiroData[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
    }

    public int[][] getTabuleiro() {
        return tabuleiro.getTabuleiro();
    }
}
