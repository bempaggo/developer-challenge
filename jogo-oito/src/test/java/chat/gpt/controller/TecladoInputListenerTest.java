package chat.gpt.controller;

import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.awt.event.KeyEvent;


import static chat.gpt.view.Constantes.*;

import static org.junit.jupiter.api.Assertions.*;

public class TecladoInputListenerTest {

    private static class TecladoInputListenerMock implements TecladoInputListener {
        private int[] input;

        @Override
        public void processarInput(int[] input) {
            this.input = input;
        }
    }

    @Test
    public void testKeyPressed_Up() {
        TecladoInputListenerMock listener = new TecladoInputListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_UP, listener.input);
    }

    @Test
    public void testKeyPressed_Down() {
        TecladoInputListenerMock listener = new TecladoInputListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_DOWN, listener.input);
    }

    @Test
    public void testKeyPressed_Left() {
        TecladoInputListenerMock listener = new TecladoInputListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_LEFT, listener.input);
    }

    @Test
    public void testKeyPressed_Right() {
        TecladoInputListenerMock listener = new TecladoInputListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertArrayEquals(MOVE_RIGHT, listener.input);
    }

    @Test
    public void testKeyPressed_OtherKey() {
        TecladoInputListenerMock listener = new TecladoInputListenerMock();
        KeyEvent event = new KeyEvent(new Component() {
        }, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED);
        listener.keyPressed(event);
        assertNull(listener.input);
    }

}