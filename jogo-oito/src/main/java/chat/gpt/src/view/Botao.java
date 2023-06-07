package chat.gpt.src.view;

import javax.swing.*;

public class Botao {

    private final JButton jButton;
    private Integer valor;

    public Botao(JButton jButton, Integer valor) {
        this.jButton = jButton;
        this.valor = valor;
    }

    public JButton getjButton() {
        return jButton;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}

