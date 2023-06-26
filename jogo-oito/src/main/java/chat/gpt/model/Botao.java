package chat.gpt.model;

import javax.swing.JButton;

public class Botao extends JButton {
    private int linha;
    private int coluna;

    public Botao(int linha, int coluna) {
        super();
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}