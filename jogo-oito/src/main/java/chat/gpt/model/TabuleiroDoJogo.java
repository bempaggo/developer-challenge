package chat.gpt.model;

import chat.gpt.view.JogoDosOito;
import chat.gpt.controller.LogicaDoJogo;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class TabuleiroDoJogo {

    private static final int TAMANHO_TABULEIRO = 3;
    private static final int CELULA_VAZIA = 0;

    private int[][] tabuleiro = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private final JButton[][] botoes = new JButton[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    private final JogoDosOito jogo;

    public TabuleiroDoJogo(JogoDosOito jogo)  {
        this.jogo = jogo;
        this.inicializarBotoes();
    }

    public JButton[][] getBotoes() {
        return botoes;
    }

    public JogoDosOito getJogo() {
        return jogo;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getValorCelula(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    public void setValorCelula(int linha, int coluna, int valor) {
        tabuleiro[linha][coluna] = valor;
    }

    private void inicializarBotoes() {
        for (int i = CELULA_VAZIA; i < TAMANHO_TABULEIRO; ++i) {
            for (int j = CELULA_VAZIA; j < TAMANHO_TABULEIRO; ++j) {
                JButton botao = criarBotao();
                botoes[i][j] = botao;
                jogo.add(botao);
            }
        }
        embaralharTabuleiro();
        atualizarBotoes();
    }

    private JButton criarBotao() {
        JButton botao = new JButton();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        botao.addActionListener(new LogicaDoJogo(this));
        return botao;
    }

    public void atualizarBotoes() {
        for (int i = CELULA_VAZIA; i < TAMANHO_TABULEIRO; ++i) {
            for (int j = CELULA_VAZIA; j < TAMANHO_TABULEIRO; ++j) {
                JButton botao = botoes[i][j];
                int valor = getValorCelula(i, j);
                atualizarNumeroBotao(botao, valor);
            }
        }
    }

    private void atualizarNumeroBotao(JButton botao, int valor) {
        botao.setText(valor == CELULA_VAZIA ? "" : String.valueOf(valor));
    }

    public void embaralharTabuleiro() {
        SecureRandom random = new SecureRandom();
        for (int i = TAMANHO_TABULEIRO - 1; i >= 0; --i) {
            for (int j = TAMANHO_TABULEIRO - 1; j >= 0; --j) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                int temp = getValorCelula(i, j);
                setValorCelula(i, j, getValorCelula(m, n));
                setValorCelula(m, n, temp);
            }
        }
    }

    public void reiniciarJogo() {
        embaralharTabuleiro();
        atualizarBotoes();
    }

}
