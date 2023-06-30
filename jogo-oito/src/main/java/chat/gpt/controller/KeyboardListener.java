package chat.gpt.controller;

import static chat.gpt.util.Constants.*;

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
            default:
                break;
        } 
        
        processInput(input);
    }

    void processInput(int[] input);

}