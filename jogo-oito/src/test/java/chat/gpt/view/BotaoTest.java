package chat.gpt.view;

import chat.gpt.src.view.Botao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class BotaoTest {

    @Test
    public void testGetValor() {
        // Arrange
        JButton jButton = new JButton();
        Integer valor = 5;
        Botao botao = new Botao(jButton, valor);

        // Act
        Integer valorRetornado = botao.getValor();

        // Assert
        Assertions.assertEquals(valor, valorRetornado);
    }

    @Test
    public void testSetValor() {
        // Arrange
        JButton jButton = new JButton();
        Integer valorInicial = 3;
        Integer novoValor = 7;
        Botao botao = new Botao(jButton, valorInicial);

        // Act
        botao.setValor(novoValor);
        Integer valorAtualizado = botao.getValor();

        // Assert
        Assertions.assertEquals(novoValor, valorAtualizado);
    }
}

