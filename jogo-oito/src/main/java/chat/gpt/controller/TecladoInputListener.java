package chat.gpt.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static chat.gpt.view.Constantes.*;

public interface TecladoInputListener extends KeyListener {

    @Override
    default void keyTyped(KeyEvent e) {
    }

    @Override
    default void keyReleased(KeyEvent e) {
    }

    @Override
    default void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int[] input = null;
        switch (keyCode) {
            case KeyEvent.VK_UP:
                input = MOVE_UP;
                break;
            case KeyEvent.VK_DOWN:
                input = MOVE_DOWN;
                break;
            case KeyEvent.VK_LEFT:
                input = MOVE_LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                input = MOVE_RIGHT;
                break;
        }
        processarInput(input);
    }

    void processarInput(int[] input);
}
