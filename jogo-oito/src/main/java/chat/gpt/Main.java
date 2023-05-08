package chat.gpt;

public class Main {
	public static void main(String[] args) {
		JogoDosOito jogo = new JogoDosOito();
		JogoDosOitoGUI gui = new JogoDosOitoGUI(jogo);
		gui.criarJanela();
	}
}