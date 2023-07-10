package chat.gpt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.ResetButton;

public class InputAdapter extends KeyAdapter implements ActionListener {
    private final MovementInterface service;
    private final ControllerInterface controller;

    public InputAdapter(MovementInterface service, ControllerInterface controller) {
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
            // interrompe o fluxo de execução do método, não é necessário fazer nada além disso
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ResetButton) {
            controller.resetGame();
            return;
        }
    }
}
