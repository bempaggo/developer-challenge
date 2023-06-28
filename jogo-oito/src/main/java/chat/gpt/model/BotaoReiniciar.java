package chat.gpt.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gpt.controller.BotaoListener;

public class BotaoReiniciar extends Botao implements ActionListener {

    private BotaoListener listener;

    public BotaoReiniciar(BotaoListener listener) {
        super("Reiniciar");
        this.listener = listener;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.reiniciarJogo();
    }
}