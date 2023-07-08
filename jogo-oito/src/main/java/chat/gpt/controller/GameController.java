package chat.gpt.controller;

import java.awt.event.KeyEvent;
import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.GridInterface;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.GameGUI;

public class GameController implements ButtonActionListener, KeyboardListener {

    private MovementInterface service;
    private GameGUI view;
    private GridInterface grid;

    public GameController(MovementInterface service, GameGUI view, GridInterface grid) {
        this.service = service;
        this.view = view;
        this.grid = grid;
    }

    public void setButtons(GameGUI view) {
        this.view = view;
    }

    @Override
    public void processInput(int input) {
        try {
            move(input);
            view.updateGrid();
            if (gameIsComplete()) {
                MessagePopUp.showMessage("Parabéns, você venceu!");
            }
        } catch (ImpossibleMoveException impossibleMoveException) {
            // impede checar se o jogo foi completado
        }
    }

    public void move(int keyCode) throws ImpossibleMoveException {
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP -> service.moveUp();
                case KeyEvent.VK_DOWN -> service.moveDown();
                case KeyEvent.VK_LEFT -> service.moveLeft();
                case KeyEvent.VK_RIGHT -> service.moveRight();
                default -> throw new PressedKeyDoesNothingException();
            }
        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            MessagePopUp.showMessage(pressedKeyDoesNothingException.getMessage());
        } 
    }

    @Override
    public void resetGame() {
        grid.reset();
        view.updateGrid();
    }

    public boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

    // temp method for not breaking things apart
    public List<Integer> gridActualState() {
        return grid.getGridData();
    }

}