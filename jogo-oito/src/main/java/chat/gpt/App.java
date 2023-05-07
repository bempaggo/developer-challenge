package chat.gpt;

public class App {
    public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro();
		TecladoListener listener = new TecladoListener(tabuleiro);
		tabuleiro.addKeyListener(listener);
	}
}
