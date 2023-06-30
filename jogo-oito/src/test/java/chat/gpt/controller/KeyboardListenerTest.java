package chat.gpt.controller;

import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.awt.event.KeyEvent;

import static chat.gpt.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class KeyboardListenerTest {

    private static class KeyboardListenerMock implements KeyboardListener {
        private int[] input;

        @Override
        public void processInput(int[] input) {
            this.input = input;
        }
    }

    @Test
    public void testKeyPressed_Up() {
        KeyboardListenerMock listener = new KeyboardListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_UP, listener.input);
    }

    @Test
    public void testKeyPressed_Down() {
        KeyboardListenerMock listener = new KeyboardListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_DOWN, listener.input);
    }

    @Test
    public void testKeyPressed_Left() {
        KeyboardListenerMock listener = new KeyboardListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_LEFT, listener.input);
    }

    @Test
    public void testKeyPressed_Right() {
        KeyboardListenerMock listener = new KeyboardListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_RIGHT, listener.input);
    }

    @Test
    public void testKeyPressed_OtherKey() {
        KeyboardListenerMock listener = new KeyboardListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertNull(listener.input);
    }

}