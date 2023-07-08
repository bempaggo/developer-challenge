package chat.gpt.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.util.MessagePopUp;

public class KeyboardAdapter extends KeyAdapter {
    private final MovementInterface service;
    private final GameController controller;

    public KeyboardAdapter(MovementInterface service, GameController controller) {
        this.service = service;
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processInput(e.getKeyCode());
    }

    private void processInput(int keyCode) throws ImpossibleMoveException {
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP -> service.moveUp();
                case KeyEvent.VK_DOWN -> service.moveDown();
                case KeyEvent.VK_LEFT -> service.moveLeft();
                case KeyEvent.VK_RIGHT -> service.moveRight();
                default -> throw new PressedKeyDoesNothingException();
            }
            controller.notifyMove();

        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            MessagePopUp.showMessage(pressedKeyDoesNothingException.getMessage());
        } catch (ImpossibleMoveException impossibleMoveException) {
            MessagePopUp.showMessage(impossibleMoveException.getMessage());
        }
    }
}
