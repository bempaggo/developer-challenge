package chat.gpt.model;

public class JogoDosOito {

    private Tabuleiro tabuleiro;

    public JogoDosOito() {
        tabuleiro = new Tabuleiro();
    }

    public void mover(int linha, int coluna) {
        int linhaVazia = -1;
        int colunaVazia = -1;
        int[][] tabuleiroData = tabuleiro.getTabuleiro();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiroData[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }

        int novaLinha = linhaVazia + linha;
        int novaColuna = colunaVazia + coluna;

        if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
            // Movimento inválido
            return;
        }

        tabuleiroData[linhaVazia][colunaVazia] = tabuleiroData[novaLinha][novaColuna];
        tabuleiroData[novaLinha][novaColuna] = 0;

        if (jogoConcluido()) {
            System.out.println("Parabéns, você venceu!");
            reiniciarJogo();
        }
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
