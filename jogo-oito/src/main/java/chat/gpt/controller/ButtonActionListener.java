package chat.gpt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface ButtonActionListener extends ActionListener {

    @Override
    void actionPerformed(ActionEvent e);
    
}