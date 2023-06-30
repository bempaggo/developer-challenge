package chat.gpt.controller;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.Game;
import chat.gpt.view.GameGUI;

public class GameService implements KeyboardListener, ResetGameButtonListener {

    private Game jogo;
    private GameGUI view;

    public GameService(Game jogo, GameGUI view) {
        this.jogo = jogo;
        this.view = view;
    }

    @Override
    public void resetGame() {
        jogo.restartGame();
        view.updateGrid();
    }
    @Override
    public void processInput(int[] input) {
        try {
            jogo.move(input);
            view.updateGrid();
            if (jogo.gameIsComplete()) {
                view.showMessage("Parabéns, você venceu!");
            }
        } catch (ImpossibleMoveException error) {
            view.showMessage(error.getMessage());
        } catch (PressedKeyDoesNothingException error) {
            view.showMessage(error.getMessage());           
        } 
    }

}