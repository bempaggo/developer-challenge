package chat.gpt;

import javax.swing.JButton;

public class Tabuleiro {

    private int[][] tabuleiro;
    private JButton[][] botoes;
    private GeradorAleatorioTabuleiro tab = new GeradorAleatorioTabuleiro();

    public Tabuleiro(JButton[][] botoes) {
        this.tabuleiro = tab.gerarNumeros();
        this.botoes = botoes;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void atualizarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = botoes[i][j];
                int valor = tabuleiro[i][j];
                if (valor == 0) {
                    botao.setText("");
                } else {
                    botao.setText(String.valueOf(valor));
                }
            }
        }
    }

    public void reiniciarJogo() {
        int[][] newTabuleiro = tab.gerarNumeros();
        setTabuleiro(newTabuleiro);
        atualizarTabuleiro();
    }

    public boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public void moverPeca(int linha, int coluna) {
        int linhaVazia = -1;
        int colunaVazia = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == 0) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }
        int novaLinha = linhaVazia + linha;
        int novaColuna = colunaVazia + coluna;

        tabuleiro[linhaVazia][colunaVazia] = tabuleiro[novaLinha][novaColuna];
        tabuleiro[novaLinha][novaColuna] = 0;
        atualizarTabuleiro();
    }
}
