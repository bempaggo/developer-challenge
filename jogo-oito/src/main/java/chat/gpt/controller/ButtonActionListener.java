package chat.gpt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface ButtonActionListener extends ActionListener {

    @Override
    default void actionPerformed(ActionEvent e) { 
        resetGame();
            
    }

    void resetGame();

}