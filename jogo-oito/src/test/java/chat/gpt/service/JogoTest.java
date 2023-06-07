package chat.gpt.service;

import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;
import chat.gpt.src.servico.Jogo;
import chat.gpt.src.servico.RegraImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {

    private Jogo jogo;
    private Tabuleiro tabuleiro;

    @BeforeEach
    public void setup() {
        jogo = new Jogo(new RegraImpl());
        tabuleiro = jogo.getTabuleiro();
    }

    @Test
    public void testGetTabuleiro() {
        // Act
        Tabuleiro jogoTabuleiro = jogo.getTabuleiro();

        // Assert
        Assertions.assertEquals(tabuleiro, jogoTabuleiro);
    }

    @Test
    public void testEmbaralharPecas() {
        // Act
        jogo.embaralharPecas();

        // Assert
        Boolean pecasOrdenadas = jogo.getTabuleiro().pecas().stream().allMatch(Peca::posicaoCorreta);

        Assertions.assertEquals(Boolean.FALSE, pecasOrdenadas);
    }

    @Test
    public void testReiniciar() {
        // Arrange
        jogo.embaralharPecas();

        // Act
        jogo.reiniciar();

        Boolean pecasOrdenadas = jogo.getTabuleiro().pecas().stream().allMatch(Peca::posicaoCorreta);
        // Assert
        Assertions.assertEquals(Boolean.TRUE, pecasOrdenadas);
    }

    @Test
    public void testFazerMovimentoMovimentoValido() {
        // Arrange
        Peca pecaMovida = tabuleiro.getPecaPorValor(6);
        Integer posicaoAntes = pecaMovida.getPosicao();

        // Act
        jogo.fazerMovimento(6);

        // Assert
        Assertions.assertNotEquals(posicaoAntes, pecaMovida.getPosicao());
    }

    @Test
    public void testFazerMovimentoMovimentoInvalido() {
        // Arrange
        Peca pecaMovida = tabuleiro.getPecaPorValor(1);
        Integer posicaoAntes = pecaMovida.getPosicao();

        // Act
        jogo.fazerMovimento(1);

        // Assert
        Assertions.assertEquals(pecaMovida.getPosicao(), posicaoAntes);
    }

    @Test
    public void testVerificarVitoriaFalse() {

        // Act
        jogo.embaralharPecas();
        Boolean vitoria = jogo.verificarVitoria();

        // Assert
        Assertions.assertFalse(vitoria);
    }

    @Test
    public void testVerificarVitoriaTrue() {
        // Act
        Boolean vitoria = jogo.verificarVitoria();

        // Assert
        Assertions.assertTrue(vitoria);
    }
}

