package chat.gpt.model;

import java.util.Arrays;

import chat.gpt.exception.MovimentoInvalidoException;
import chat.gpt.exception.PosicaoVaziaNaoEncontradaException;

import static chat.gpt.view.Constantes.*;

public class JogoDosOito {
    private Tabuleiro tabuleiro;

    public JogoDosOito() {
        tabuleiro = new Tabuleiro();
    }

    public void mover(int[] deslocamento) {
        int linhaDeslocamento = deslocamento[0];
        int colunaDeslocamento = deslocamento[1];

        int[] posicaoVazia = encontrarPosicaoVazia();
        int linhaVazia = posicaoVazia[0];
        int colunaVazia = posicaoVazia[1];

        int novaLinha = linhaVazia + linhaDeslocamento;
        int novaColuna = colunaVazia + colunaDeslocamento;

        if (posicaoValida(novaLinha, novaColuna)) {
            trocarPosicoes(linhaVazia, colunaVazia, novaLinha, novaColuna);
        } else throw new MovimentoInvalidoException();
    }

    private int[] encontrarPosicaoVazia() {
        int[][] tabuleiroData = estadoAtualTabuleiro();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (tabuleiroData[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }

        throw new PosicaoVaziaNaoEncontradaException();
    }

    private boolean posicaoValida(int linha, int coluna) {
        return (linha >= 0 && linha < boardWidth) &&
                (coluna >= 0 && coluna < boardLength);
                
    }

    private void trocarPosicoes(int linha1, int coluna1, int linha2, int coluna2) {
        int[][] tabuleiroData = estadoAtualTabuleiro();
        int temp = tabuleiroData[linha1][coluna1];
        tabuleiroData[linha1][coluna1] = tabuleiroData[linha2][coluna2];
        tabuleiroData[linha2][coluna2] = temp;
    }


    public boolean jogoConcluido() {
        return Arrays.deepEquals(estadoAtualTabuleiro(), JOGO_CONCLUIDO);
    }

    public void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
    }

    public int[][] estadoAtualTabuleiro() {
        return tabuleiro.getTabuleiro();
    }
}
