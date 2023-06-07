package chat.gpt.modelo;

import chat.gpt.src.modelo.Peca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PecaTest {

    private Peca peca;

    @BeforeEach
    public void setup() {
        peca = new Peca(1, 0);
    }

    @Test
    public void testGetValor() {
        // Act
        Integer valor = peca.getValor();

        // Assert
        Assertions.assertEquals(1, valor);
    }

    @Test
    public void testGetPosicao() {
        // Act
        Integer posicao = peca.getPosicao();

        // Assert
        Assertions.assertEquals(0, posicao);
    }

    @Test
    public void testSetPosicao() {
        // Arrange
        Integer novaPosicao = 2;

        // Act
        peca.setPosicao(novaPosicao);

        // Assert
        Assertions.assertEquals(novaPosicao, peca.getPosicao());
    }

    @Test
    public void testGetTextoValor() {
        // Arrange
        peca = new Peca(9, 1);

        // Act
        String textoValor = peca.getTextoValor();

        // Assert
        Assertions.assertEquals(" ", textoValor);
    }

    @Test
    public void testEhPecaVaziaTrue() {
        // Arrange
        peca = new Peca(9, 1);

        // Act
        Boolean ehPecaVazia = peca.ehPecaVazia();

        // Assert
        Assertions.assertTrue(ehPecaVazia);
    }

    @Test
    public void testEhPecaVaziaFalse() {
        // Act
        Boolean ehPecaVazia = peca.ehPecaVazia();

        // Assert
        Assertions.assertFalse(ehPecaVazia);
    }

    @Test
    public void testPosicaoCorretaTrue() {
        // Act
        Boolean posicaoCorreta = peca.posicaoCorreta();

        // Assert
        Assertions.assertTrue(posicaoCorreta);
    }

    @Test
    public void testPosicaoCorretaFalse() {
        // Arrange
        peca = new Peca(5, 6);
        // Act
        Boolean posicaoCorreta = peca.posicaoCorreta();

        // Assert
        Assertions.assertFalse(posicaoCorreta);
    }
}
