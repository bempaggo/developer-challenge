package chat.gpt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.MovementInterface;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.Button;
import chat.gpt.view.ResetButton;

public class InputAdapter extends KeyAdapter implements ActionListener {

    private final MovementInterface moveRuleset;
    private final ControllerInterface controller;

    public InputAdapter(MovementInterface moveRuleset, ControllerInterface controller) {
        this.moveRuleset = moveRuleset;
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processInput(e.getKeyCode());
    }

    private void processInput(int keyCode) {
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP -> moveRuleset.moveUp();
                case KeyEvent.VK_DOWN -> moveRuleset.moveDown();
                case KeyEvent.VK_LEFT -> moveRuleset.moveLeft();
                case KeyEvent.VK_RIGHT -> moveRuleset.moveRight();
                default -> throw new PressedKeyDoesNothingException();
            }
            controller.notifyMove();

        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            MessagePopUp.showMessage(pressedKeyDoesNothingException.getMessage());
        } catch (ImpossibleMoveException impossibleMoveException) {
            // interrompe o fluxo de execução do método
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // interrompe o fluxo de execução do método;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof ResetButton) {
            controller.resetGame();
            return;
        }

        try {
            // Obter o valor do botão clicado
            Button button = (Button) e.getSource();
            int value = Integer.parseInt(button.getText());

            moveRuleset.move(value);
            controller.notifyMove();
        } catch (ImpossibleMoveException impossibleMoveException) {
            // interrompe o fluxo de execução do método
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // interrompe o fluxo de execução do método;
        } catch (NumberFormatException numberFormatException) {
            // interrompe o fluxo de execução do método;
        }
    }
    
}
