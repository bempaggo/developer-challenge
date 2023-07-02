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
        
        int[] input = switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> MOVE_UP;
            case KeyEvent.VK_DOWN -> MOVE_DOWN;
            case KeyEvent.VK_LEFT -> MOVE_LEFT;
            case KeyEvent.VK_RIGHT -> MOVE_RIGHT;
            default -> null;
        };

        processInput(input);
    }

    void processInput(int[] input);

}