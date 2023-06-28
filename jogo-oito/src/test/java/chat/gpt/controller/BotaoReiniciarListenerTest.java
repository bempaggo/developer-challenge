package chat.gpt.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BotaoReiniciarListenerTest {

    private boolean jogoReiniciado;

    @Test
    public void testReiniciarJogo() {
        BotaoReiniciarListener listener = () -> jogoReiniciado = true;

        // Chamar o método reiniciarJogo()
        listener.reiniciarJogo();

        // Verificar se o jogo foi reiniciado corretamente
        Assertions.assertTrue(jogoReiniciado);
    }
}
