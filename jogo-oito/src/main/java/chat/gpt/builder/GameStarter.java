package chat.gpt.builder;

import java.awt.event.KeyAdapter;

import chat.gpt.controller.ControllerInterface;
import chat.gpt.controller.GameController;
import chat.gpt.controller.GameService;
import chat.gpt.controller.KeyboardAdapter;
import chat.gpt.controller.MovementInterface;
import chat.gpt.model.Grid;
import chat.gpt.model.GridInterface;
import chat.gpt.util.GridConstants;
import chat.gpt.view.GameGUI;

public class GameStarter {

    public static void startGame() {

        // inicia o Grid com base nas constantes do enum Grid
        GridInterface grid = new Grid(GridConstants.GRID_SIZE.getMeasure(), GridConstants.RANDOM_GRID.useRandomGrid());

        /* inicia o GameService, que precisa do GridInterface para receber índices e o estado da lista
        para poder movimentar as peças */
        MovementInterface gameService = new GameService(grid);

        /* inicia o GameController, que precisa do GameService e do Grid para, com base no input do usuário,
        movimentar as peças e atualizar a tela */
        ControllerInterface gameController = new GameController(grid);

        /* inicializa o KeyboardAdapter, que precisa do MovimentInterface para chamar os métodos de movimentação
         e tem de avisar o Controller que fez isso */
        KeyAdapter keyboardAdapter = new KeyboardAdapter(gameService, gameController);

        new GameGUI(gameController, keyboardAdapter);

    }

}

