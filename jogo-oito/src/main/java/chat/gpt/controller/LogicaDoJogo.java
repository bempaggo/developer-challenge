package chat.gpt.controller;

import chat.gpt.model.TabuleiroDoJogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicaDoJogo implements ActionListener {

    private final TabuleiroDoJogo tabuleiroDoJogo;
    private static final int CELULA_VAZIA = 0;
    private static final int TAMANHO_TABULEIRO = 3;

    public LogicaDoJogo(TabuleiroDoJogo tabuleiroDoJogo) {
        this.tabuleiroDoJogo = tabuleiroDoJogo;
    }

    private boolean movimentarPeca(int linha, int coluna) {
        int[][] tabuleiroArray = tabuleiroDoJogo.getTabuleiro();
        int[][] posicoesVizinhas = {{linha - 1, coluna}, {linha + 1, coluna}, {linha, coluna - 1}, {linha, coluna + 1}};

        for (int[] posicao : posicoesVizinhas) {
            int linhaVizinha = posicao[0];
            int colunaVizinha = posicao[1];

            if (linhaVizinha >= 0 && linhaVizinha < TAMANHO_TABULEIRO &&
                    colunaVizinha >= 0 && colunaVizinha < TAMANHO_TABULEIRO &&
                    tabuleiroArray[linhaVizinha][colunaVizinha] == CELULA_VAZIA) {
                trocarCelulas(tabuleiroArray, linha, coluna, linhaVizinha, colunaVizinha);
                return true;
            }
        }
        return false;
    }

    private void trocarCelulas(int[][] tabuleiroArray, int linha1, int coluna1, int linha2, int coluna2) {
        int temp = tabuleiroArray[linha1][coluna1];
        tabuleiroArray[linha1][coluna1] = tabuleiroArray[linha2][coluna2];
        tabuleiroArray[linha2][coluna2] = temp;
    }

    public boolean verificarJogoConcluido() {
        int count = 1;
        int[][] tabuleiroArray = tabuleiroDoJogo.getTabuleiro();

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (tabuleiroArray[i][j] != count % (TAMANHO_TABULEIRO * TAMANHO_TABULEIRO)) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        JButton botaoClicado = (JButton) e.getSource();
        Point posicaoClicada = obterPosicaoClicada(botaoClicado);

        if (posicaoClicada != null && movimentarPeca(posicaoClicada.x, posicaoClicada.y)) {
            tabuleiroDoJogo.atualizarBotoes();
            if (verificarJogoConcluido()) {
                JOptionPane.showMessageDialog(tabuleiroDoJogo.getJogo(), "Parabéns, você venceu!");
                tabuleiroDoJogo.reiniciarJogo();
            }
        }
    }

    private Point obterPosicaoClicada(JButton botao) {
        JButton[][] botoes = tabuleiroDoJogo.getBotoes();

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                if (botao == botoes[i][j]) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

}
