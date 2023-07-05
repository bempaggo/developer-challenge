package chat.gpt.controller;

import java.awt.event.KeyEvent;

import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.Game;
import chat.gpt.view.GameGUI;

public class GameService implements ButtonActionListener, KeyboardListener {

    private Game game;
    private GameGUI view;

    public GameService(Game game, GameGUI view) {
        this.game = game;
        this.view = view;
    }

    public void setView(GameGUI view) {
        this.view = view;
    }

    @Override
    public void processInput(int input) {
        move(input);
        view.updateGrid();
        if (game.gameIsComplete()) {
            view.showMessage("Parabéns, você venceu!");
        }
    }

    public void move(int keyCode) {
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP -> game.moveUp();
                case KeyEvent.VK_DOWN -> game.moveDown();
                case KeyEvent.VK_LEFT -> game.moveLeft();
                case KeyEvent.VK_RIGHT -> game.moveRight();
                default -> throw new PressedKeyDoesNothingException();
            }
        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            view.showMessage(pressedKeyDoesNothingException.getMessage());
        }
    }

    @Override
    public void resetGame() {
        game.resetGrid();
        view.updateGrid();
    }

}