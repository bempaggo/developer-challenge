package chat.gpt.model;

import javax.swing.JButton;
import java.awt.Font;

public class Botao extends JButton {
    private int linha;
    private int coluna;

    public Botao(int linha, int coluna) {
        super();
        this.linha = linha;
        this.coluna = coluna;
    }

    public Botao() {
        super();
        
    }

    public Botao(String texto) {
        super(texto);
        
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public static Botao criarBotao(String texto) {
        return new Botao(texto);
    }

    public static Botao criarBotaoVazio() {
        Botao botao = new Botao();
        botao.setFont(new Font("Arial", Font.BOLD, 36));
        return botao;
    }
}
