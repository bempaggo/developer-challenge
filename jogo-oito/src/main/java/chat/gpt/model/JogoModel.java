package chat.gpt.model;


import javax.swing.*;

public class JogoModel {

    private TabuleiroModel tabuleiro;

    // acoplamento obrigatorio com o swing
    private JButton[] botoes = new JButton[9];
    private JButton botaoReiniciar;

    public JogoModel(TabuleiroModel tabuleiro, JButton[] botoes, JButton botaoReiniciar) {
        this.tabuleiro = tabuleiro;
        this.botoes = botoes;
        this.botaoReiniciar = botaoReiniciar;
    }

    public JogoModel() {
        this.tabuleiro = new TabuleiroModel();
    }

    public TabuleiroModel getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(TabuleiroModel tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public JButton[] getBotoes() {
        return botoes;
    }

    public void setBotoes(JButton[] botoes) {
        this.botoes = botoes;
    }

    public JButton getBotaoReiniciar() {
        return botaoReiniciar;
    }

    public void setBotaoReiniciar(JButton botaoReiniciar) {
        this.botaoReiniciar = botaoReiniciar;
    }

}