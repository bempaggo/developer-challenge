package chat.gpt.models;

import java.awt.Font;

import javax.swing.JButton;

public class Botao extends JButton {

    private String valor;

    public Botao() {
        this("NaN");
    }

    public Botao(String valor) {
        super(valor);
        this.valor = valor;
        setFont(new Font("Arial", Font.BOLD, 36));
        atualizarTexto();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
        atualizarTexto();
    }

    public void atualizarTexto() {
        if(valor.equals("0"))
            setText("");
        else
            setText(valor);
    }

}
