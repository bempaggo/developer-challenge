package initializer;

import controller.ControllerInterface;
import controller.GameController;
import controller.InputController;
import model.Board;
import model.BoardInterface;
import model.MoveRuleset;
import model.MovementInterface;
import util.BoardConstants;
import view.GameUI;

public class GameInitializer {

    public static void startGame() {

        BoardInterface board = new Board(BoardConstants.SIZE.getMeasure(),
                BoardConstants.WIDTH.getMeasure());
    
        MovementInterface moveRuleset = new MoveRuleset();
        ControllerInterface gameController = new GameController();
        GameUI view = new GameUI();
        InputController inputAdapter = new InputController();

        board.registerObserver(view);
        moveRuleset.setBoard(board);

        gameController.setBoard(board);
        gameController.setView(view);
    
        inputAdapter.setMovementInterface(moveRuleset);
        inputAdapter.setControllerInterface(gameController);
    
        view.setControllerInterface(gameController);
        view.setKeyboardAdapter(inputAdapter);
        view.setButtonListener(inputAdapter);
    
        inputAdapter.configurate();
        view.configurate();
        
    }
    
}
