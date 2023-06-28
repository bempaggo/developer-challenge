package main.java.chat.gpt;

import javax.swing.*;

public class Button extends ValueInterface{
    private final JButton jButton;

    public Button(JButton jButton, Integer value) {
        this.jButton = jButton;
        this.value = value;
    }

    public JButton getjButton() {
        return jButton;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value){
        this.value = value;
    }
}
