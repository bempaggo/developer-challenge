package chat.gpt;

import chat.gpt.controller.GameController;
import chat.gpt.model.GameModel;
import chat.gpt.view.GameView;

public class JogoDosOito {
    public static void main(String[] args) {
        // Cria instancia do GameView
        GameView view = new GameView();
        // Cria instancia do GameModel e passa view como parametro
        GameModel model = new GameModel(view);
        // Cria instancia do GameController passando model e view como parametro
        GameController controller = new GameController(model, view);
    }
}