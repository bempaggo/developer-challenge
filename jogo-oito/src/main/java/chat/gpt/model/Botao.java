package chat.gpt.model;

import javax.swing.JButton;

import static chat.gpt.view.Constantes.*;

public class Botao extends JButton {

    /*private int linha;
    private int coluna; */

    private Botao() {
        super();
        setFont(FONTE_PADRAO); 
    }

    /* protected Botao(int linha, int coluna) {
        this();
        this.linha = linha;
        this.coluna = coluna;
    } */

    protected Botao(String texto) {
        this();
        setText(texto);
    }

    /* public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    } */

    public static Botao criarBotao(String texto) {
        return new Botao(texto);
    }

    public static Botao criarBotaoVazio() {
        return new Botao();
    }
}
