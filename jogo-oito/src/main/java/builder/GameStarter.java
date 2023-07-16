package builder;

import controller.ControllerInterface;
import controller.GameController;
import controller.InputAdapter;
import model.Board;
import model.BoardInterface;
import model.MoveRuleset;
import model.MovementInterface;
import util.GridConstants;
import view.GameUI;

public class GameStarter {

    public static void startGame() {

        BoardInterface board = new Board(GridConstants.SIZE.getMeasure(),
                GridConstants.WIDTH.getMeasure(),
                GridConstants.RANDOMNESS.useRandomGrid());
    
        MovementInterface moveRuleset = new MoveRuleset();
        ControllerInterface gameController = new GameController();
        GameUI view = new GameUI();
        InputAdapter inputAdapter = new InputAdapter();
        
        moveRuleset.setGrid(board);

        gameController.setBoard(board);
        gameController.setView(view);
    
        inputAdapter.setMovementInterface(moveRuleset);
        inputAdapter.setControllerInterface(gameController);
    
        view.setControllerInterface(gameController);
        view.setKeyboardAdapter(inputAdapter);
        view.setButtonListener(inputAdapter);
    
        inputAdapter.configurate();
        view.configurate();
        view.updateBoard();
    }
    
}
