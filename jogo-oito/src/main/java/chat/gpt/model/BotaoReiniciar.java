package chat.gpt.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gpt.controller.BotaoReiniciarListener;

public class BotaoReiniciar extends Botao implements ActionListener {

    private static final String BotaoReiniciarNome = "Reiniciar";

    private BotaoReiniciarListener listener;

    public BotaoReiniciar(BotaoReiniciarListener listener) {
        super(BotaoReiniciarNome);
        this.listener = listener;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.reiniciarJogo();
    }
}