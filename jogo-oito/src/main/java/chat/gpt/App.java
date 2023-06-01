package chat.gpt;

import chat.gpt.src.servico.Jogo;
import chat.gpt.src.view.View;

public class App {

    public static void main(String[] args) {
        jogoDosOito();
    }

    private static void jogoDosOito() {
        new View(new Jogo());
    }
}
