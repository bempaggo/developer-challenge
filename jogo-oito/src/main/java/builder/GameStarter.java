package builder;

import controller.ControllerInterface;
import controller.GameController;
import controller.InputAdapter;
import model.Grid;
import model.GridInterface;
import model.MoveRuleset;
import model.MovementInterface;
import util.GridConstants;
import view.GameUI;

public class GameStarter {

    public static void startGame() {

        GridInterface grid = new Grid(GridConstants.SIZE.getMeasure(),
                GridConstants.WIDTH.getMeasure(),
                GridConstants.RANDOMNESS.useRandomGrid());
    
        MovementInterface moveRuleSet = new MoveRuleset();
        ControllerInterface gameController = new GameController();
        GameUI view = new GameUI();
        InputAdapter inputAdapter = new InputAdapter();
        
        moveRuleSet.setGrid(grid);

        gameController.setGrid(grid);
        gameController.setView(view);
    
        inputAdapter.setMovementInterface(moveRuleSet);
        inputAdapter.setControllerInterface(gameController);
    
        view.setControllerInterface(gameController);
        view.setKeyboardAdapter(inputAdapter);
        view.setButtonListener(inputAdapter);
    
        inputAdapter.configurate();
        view.configurate();
        view.updateGrid();
    }
    
}
