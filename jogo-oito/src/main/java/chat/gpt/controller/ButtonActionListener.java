package chat.gpt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chat.gpt.model.ResetGameButton;

public interface ButtonActionListener extends ActionListener {

    @Override
    default void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ResetGameButton) 
            resetGame();
            
    }

    void resetGame();

}