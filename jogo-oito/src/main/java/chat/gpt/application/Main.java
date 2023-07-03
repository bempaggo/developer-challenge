package chat.gpt.application;

import chat.gpt.controller.GameService;
import chat.gpt.model.Game;
import chat.gpt.view.GameGUI;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
        GameService service = new GameService(game, null); 

        GameGUI gameGUI = new GameGUI(game, service);
        service.setView(gameGUI);
	}
}