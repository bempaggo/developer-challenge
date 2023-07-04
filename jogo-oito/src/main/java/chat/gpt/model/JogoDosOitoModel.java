package chat.gpt.model;

import javax.swing.*;

public class JogoDosOitoModel {

    public int[][] tabuleiro = new int[3][3];
    private JButton[][] botoes = new JButton[3][3];
    private JButton botaoReiniciar;

    public JogoDosOitoModel(int[][] tabuleiro, JButton[][] botoes) {
        this.tabuleiro = tabuleiro;
        this.botoes = botoes;
    }

    public JogoDosOitoModel() {
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public JButton[][] getBotoes() {
        return botoes;
    }

    public void setBotoes(JButton[][] botoes) {
        this.botoes = botoes;
    }

    public JButton getBotaoReiniciar() {
        return botaoReiniciar;
    }

    public void setBotaoReiniciar(JButton botaoReiniciar) {
        this.botaoReiniciar = botaoReiniciar;
    }
}
