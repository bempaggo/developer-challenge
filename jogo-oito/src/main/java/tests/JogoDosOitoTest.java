package tests;

import chat.gpt.view.JogoDosOito;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JogoDosOitoTest {

    @Test
    public void testConfigurarJanela() {
        JogoDosOito jogo = new JogoDosOito();

        assertEquals("Jogo dos Oito", jogo.getTitle());
        assertEquals(WindowConstants.EXIT_ON_CLOSE, jogo.getDefaultCloseOperation());
        assertEquals(300, jogo.getWidth());
        assertEquals(300, jogo.getHeight());
        assertNotNull(jogo.getContentPane().getLayout());
    }

    @Test
    public void testAdicionarBotaoReiniciar() {
        JogoDosOito jogo = new JogoDosOito();

        JButton botaoReiniciar = null;
        Component[] components = jogo.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JButton && ((JButton) component).getText().equals("Reiniciar")) {
                botaoReiniciar = (JButton) component;
                break;
            }
        }
        assertNotNull(botaoReiniciar);
        assertNotNull(botaoReiniciar.getActionListeners());
        assertEquals(1, botaoReiniciar.getActionListeners().length);
    }
}
