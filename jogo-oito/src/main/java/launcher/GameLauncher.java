package launcher;


import facade.Controller;
import factories.GameFactory;
import factories.GameFactoryImpl;
import util.Board;
import view.JogoDosOito;

public class GameLauncher {

    public static void buildGame() {
        GameFactory gameFactory = new GameFactoryImpl();
        Board board = new Board(gameFactory);
        Controller controller = new Controller(board);
        JogoDosOito game = new JogoDosOito(controller);
        controller.initializeBoard();

        board.addListener(game);

        game.createButtons();
        game.configMenu();
        game.configureInterface();
    }
    public static void main(String[] args) {
        GameLauncher.buildGame();
    }

}
