package chat.gpt.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static chat.gpt.util.Constants.RESTART_BUTTON_FONT;

import chat.gpt.controller.ResetGameButtonListener;

public class ResetGameButton extends Button implements ActionListener {

    private static final String restartButtonName = "Reiniciar";

    private ResetGameButtonListener listener;

    public ResetGameButton(ResetGameButtonListener listener) {
        super(restartButtonName);
        this.setFont(RESTART_BUTTON_FONT);
        this.listener = listener;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.resetGame();
    }
}