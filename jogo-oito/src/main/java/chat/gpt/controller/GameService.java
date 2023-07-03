package chat.gpt.controller;

import java.awt.event.ActionEvent;

import chat.gpt.exception.ImpossibleMoveException;
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

    public void processInput(int[] input) {
        try {
            if (input == null)
                throw new PressedKeyDoesNothingException();
            game.move(input);
            view.updateGrid();
            if (game.gameIsComplete()) {
                view.showMessage("Parabéns, você venceu!");
            }
        } catch (ImpossibleMoveException impossibleMoveException) {
            view.showMessage(impossibleMoveException.getMessage());
        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            view.showMessage(pressedKeyDoesNothingException.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.resetGame();
        ;
        view.updateGrid();
    }

}