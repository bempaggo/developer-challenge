package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chat.gpt.controller.BotaoReiniciarListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BotaoReiniciarTest {

    private boolean reiniciarJogoChamado = false;

    @Test
    void testConstrutor() {
        BotaoReiniciarListener listener = () -> {
            // simulação da implementação
        };

        BotaoReiniciar botaoReiniciar = new BotaoReiniciar(listener);

        Assertions.assertEquals("Reiniciar", botaoReiniciar.getText());
        Assertions.assertNotNull(botaoReiniciar.getActionListeners());
        Assertions.assertEquals(1, botaoReiniciar.getActionListeners().length);
        Assertions.assertSame(botaoReiniciar, botaoReiniciar.getActionListeners()[0]);
    }

    @Test
    void testActionPerformed() {
        BotaoReiniciarListener listener = new BotaoReiniciarListener() {
            @Override
            public void reiniciarJogo() {
                reiniciarJogoChamado = true;
            }
        };

        BotaoReiniciar botaoReiniciar = new BotaoReiniciar(listener);

        ActionListener[] actionListeners = botaoReiniciar.getActionListeners();
        Assertions.assertEquals(1, actionListeners.length);

        ActionEvent mockEvent = new ActionEvent(botaoReiniciar, ActionEvent.ACTION_PERFORMED, "mockCommand");
        actionListeners[0].actionPerformed(mockEvent);

        Assertions.assertTrue(reiniciarJogoChamado);
    }
}
