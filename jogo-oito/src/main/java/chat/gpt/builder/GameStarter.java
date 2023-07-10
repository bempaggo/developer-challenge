package chat.gpt.builder;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import chat.gpt.controller.ControllerInterface;
import chat.gpt.controller.GameController;
import chat.gpt.controller.GameService;
import chat.gpt.controller.InputAdapter;
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
         e precisa passar callbacks pro controller */
        InputAdapter inputAdapter = new InputAdapter(gameService, gameController);

        /* Minha classe que cria a interface gráfica precisa temporariamente do gameController para fazer
         os botões e dos listeners para fazer teclado e mouse funcionarem no jogo. Embora a classe observer
         seja a mesma, a classe GameGUI não tem conhecimento da implementação e simplesmente passa um listener
         pra janela e outro pros botões, sem saber que é a mesma instância de classe
          */
        new GameGUI(gameController, (KeyListener) inputAdapter, (ActionListener) inputAdapter);

    }

}

