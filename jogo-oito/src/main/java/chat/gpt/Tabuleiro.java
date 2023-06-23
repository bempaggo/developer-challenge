package chat.gpt;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Tabuleiro {

    private int[][] estado;

    public Tabuleiro() {
        estado = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    }

    public int[][] getEstado() {
        return estado;
    }

    public void mover(int linha, int coluna) {
        int linhaVazia = -1;
        int colunaVazia = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado[i][j] == 0) {
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
        estado[linhaVazia][colunaVazia] = estado[novaLinha][novaColuna];
        estado[novaLinha][novaColuna] = 0;
        if (jogoConcluido()) {
            JOptionPane.showMessageDialog(null, "Parabéns, você venceu!");
            reiniciar();
        }
    }

    private boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (estado[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    void reiniciar() {
        estado = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    }
}
