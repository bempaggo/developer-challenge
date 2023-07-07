package chat.gpt.builder;

import chat.gpt.controller.GameController;
import chat.gpt.controller.GameService;
import chat.gpt.model.Grid;
import chat.gpt.util.Constants;
import chat.gpt.view.GameGUI;

public class GameStart {

    public static void startGame() {
        Grid grid = new Grid(Constants.GRID_SIZE.getMeasure(), Constants.RANDOM_GRID.useRandomGrid());
        GameService gameService = new GameService();
        GameController gameController = new GameController(gameService, null, grid);
        GameGUI gameGUI = new GameGUI(gameController);

        gameService.setGrid(grid);
        gameController.setView(gameGUI);
    }

}

