package chat.gpt;

import chat.gpt.models.observer.Tabuleiro;
import chat.gpt.models.observer.TabuleiroGUI;
import chat.gpt.models.subject.TecladoListener;

public class App {
    public static void main(String[] args) {

		Tabuleiro tabuleiro = new Tabuleiro();
		TabuleiroGUI gui = new TabuleiroGUI(tabuleiro);

		TecladoListener listener = new TecladoListener(tabuleiro);

		gui.addKeyListener(listener);
	}
}
