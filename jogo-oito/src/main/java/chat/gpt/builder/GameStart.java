package chat.gpt.builder;

import chat.gpt.controller.GameController;
import chat.gpt.controller.GameService;
import chat.gpt.model.Grid;
import chat.gpt.util.GridConstants;
import chat.gpt.view.GameGUI;

public class GameStart {

    public static void startGame() {

        // inicia o Grid com base nas constantes do enum Grid
        Grid grid = new Grid(GridConstants.GRID_SIZE.getMeasure(), GridConstants.RANDOM_GRID.useRandomGrid());

        // inicia o GameService, que precisa do ListInfoInterface para saber a posição do espaço vazio 
        // para poder movimentar as peças, 
        GameService gameService = new GameService(grid);

        // inicia o GameController, que precisa do GameService e do Grid para, com base no input do usuário,
        // movimentar as peças e atualizar a tela
        GameController gameController = new GameController(gameService, null, grid);

        // inicia o GameGUI, que precisa do GameController para poder inserir o listener do botão reiniciar(?)
        GameGUI gameGUI = new GameGUI(gameController);

        gameController.setView(gameGUI);
    }

}

