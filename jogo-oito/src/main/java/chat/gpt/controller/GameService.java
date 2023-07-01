package chat.gpt.controller;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.Game;
import chat.gpt.view.GameGUI;

public class GameService implements KeyboardListener {

    private Game game = Game.getInstance();
    private GameGUI view;

    public GameService(GameGUI view) {
        this.view = view;
    }
    
    public void processInput(int[] input) {
        try {
            if (input == null) throw new PressedKeyDoesNothingException();
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

}