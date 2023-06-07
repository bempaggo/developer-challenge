package chat.gpt;

import chat.gpt.src.servico.Jogo;
import chat.gpt.src.servico.Regra;
import chat.gpt.src.servico.RegraImpl;
import chat.gpt.src.view.View;

public class App {

    public static void main(String[] args) {
        jogoDosOito();
    }

    private static void jogoDosOito() {
        Regra regra = new RegraImpl();
        Jogo jogo = new Jogo(regra);
        new View(jogo);
    }
}
