package chat.gpt;

import chat.gpt.ui.JogoDosOitoUI;

public class JogoDosOito {

    public static final String NOME_DO_JOGO = "Jogo dos Oito";

    public static void main(String[] args) {
        new JogoDosOitoUI(JogoDosOito.NOME_DO_JOGO).iniciarJogo();
    }
}
