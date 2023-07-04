package chat.gpt.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public interface KeyboardListener extends KeyListener {

    @Override
    default void keyTyped(KeyEvent e) {
    }

    @Override
    default void keyReleased(KeyEvent e) {
    }

    @Override
    default void keyPressed(KeyEvent e) {
        processInput(e.getKeyCode());
    }

    void processInput(int input);

}