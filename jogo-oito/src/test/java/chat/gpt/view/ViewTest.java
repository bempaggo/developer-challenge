package chat.gpt.view;

import chat.gpt.mock.MockJogo;
import chat.gpt.src.exception.JogoException;
import chat.gpt.src.servico.Jogo;
import chat.gpt.src.servico.RegraImpl;
import chat.gpt.src.view.Botao;
import chat.gpt.src.view.View;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ViewTest {

    private final Jogo jogo = new Jogo(new RegraImpl());
    private List<Botao> botaoPecas;

    @BeforeEach
    public void setup() {
        View view = new View(jogo);
        botaoPecas = view.getBotaoPecas();
    }

    @Test
    public void testViewInicializacaoBotaoPecas() {
        // Assert
        Assertions.assertEquals(9, botaoPecas.size());
        for (int i = 0; i < 9; i++) {
            Assertions.assertEquals(i + 1, botaoPecas.get(i).getValor());
        }
    }

    @Test
    public void testBotaoEmbaralharChamaFuncaoEmbaralharPecas() {
        // Arrange
        MockJogo mockJogo = new MockJogo(jogo.getRegra());
        View view = new View(mockJogo);

        JButton botaoEmbaralhar = findButtonByText(view.getContentPane(), "Embaralhar");

        // Act
        botaoEmbaralhar.doClick();

        // Assert
        Assertions.assertTrue(mockJogo.isEmbaralharPecasChamado());
    }

    @Test
    public void testBotaoReiniciarChamaFuncaoReiniciar() {
        // Arrange
        MockJogo mockJogo = new MockJogo(jogo.getRegra());
        View view = new View(mockJogo);

        JButton botaoReiniciar = findButtonByText(view.getContentPane(), "Reiniciar");

        // Act
        botaoReiniciar.doClick();
        // Assert
        Assertions.assertTrue(mockJogo.isReiniciarChamado());
    }

    @Test
    public void testBotaoPecaChamaFuncaoFazerMovimento() {
        // Arrange
        JButton botaoPeca = botaoPecas.get(5).getjButton();
        int valorAntes = botaoPecas.get(5).getValor();

        MouseEvent event = new MouseEvent(
                botaoPeca,
                MouseEvent.MOUSE_CLICKED,
                System.currentTimeMillis(),
                0,
                0,
                0,
                1,
                false);

        // Act
        for (MouseListener listener : botaoPeca.getMouseListeners()) {
            listener.mouseClicked(event);
        }

        int valorDepois = botaoPecas.get(5).getValor();

        // Assert
        Assertions.assertNotEquals(valorAntes, valorDepois);
    }

    private JButton findButtonByText(Container container, String text) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JButton button) {
                if (button.getText().equals(text)) {
                    return button;
                }
            }
        }
        throw new JogoException("Botão com texto:" + text + " não foi encontrado!");
    }
}


