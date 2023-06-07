package chat.gpt.modelo;

import chat.gpt.src.modelo.Movimento;
import chat.gpt.src.modelo.Peca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MovimentoTest {

    @Test
    public void testGetMover() {
        // Arrange
        Movimento movimento = Movimento.CIMA;

        // Act
        Integer mover = movimento.getMover();

        // Assert
        Assertions.assertEquals(-3, mover);
    }

    @Test
    public void testEhValidoPecaVazia() {
        // Arrange
        Peca pecaVazia = new Peca(9, 8);

        // Act
        boolean valido = Movimento.ehValido(Movimento.CIMA, pecaVazia, pecaVazia);

        // Assert
        Assertions.assertFalse(valido);
    }

    @Test
    public void testEhValidoMovimentoValido() {
        // Arrange
        Peca peca = new Peca(2, 1);
        Peca pecaVazia = new Peca(9, 4);

        // Act
        boolean valido = Movimento.ehValido(Movimento.CIMA, pecaVazia, peca);

        // Assert
        Assertions.assertTrue(valido);
    }

    @Test
    public void testEhValidoMovimentoInvalido() {
        // Arrange
        Peca peca = new Peca(2, 1);
        Peca pecaVazia = new Peca(9, 5);

        // Act
        boolean valido = Movimento.ehValido(Movimento.DIREITA, pecaVazia, peca);

        // Assert
        Assertions.assertFalse(valido);
    }

    @Test
    public void testObterMovimentoExistente() {
        // Arrange
        Integer mover = 1;

        // Act
        Optional<Movimento> movimentoOptional = Movimento.obterMovimento(mover);

        // Assert
        Assertions.assertTrue(movimentoOptional.isPresent());
        Assertions.assertEquals(Movimento.DIREITA, movimentoOptional.get());
    }

    @Test
    public void testObterMovimentoNaoExistente() {
        // Arrange
        Integer mover = 4;

        // Act
        Optional<Movimento> movimentoOptional = Movimento.obterMovimento(mover);

        // Assert
        Assertions.assertFalse(movimentoOptional.isPresent());
    }
}

