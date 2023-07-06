package chat.gpt.builder;

import chat.gpt.controller.GameController;
import chat.gpt.controller.GameService;
import chat.gpt.view.GameGUI;

public class GameStart {

    public static void startGame() {
        GameService gameService = new GameService();
        GameController gameController = new GameController(gameService, null);
        GameGUI gameGUI = new GameGUI(gameService, gameController);
        gameController.setView(gameGUI);
    }

}

