package chat.gpt.modelo;

import chat.gpt.src.exception.JogoException;
import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;
    private List<Peca> pecas;

    @BeforeEach
    public void setup() {
        pecas = new ArrayList<>();
        IntStream.range(0, 9).forEach(i -> pecas.add(new Peca(i + 1, i)));

        tabuleiro = new Tabuleiro(pecas);
    }

    @Test
    public void testGetPecaPorPosicao() {
        // Arrange
        int posicao = 2;

        // Act
        Peca peca = tabuleiro.getPecaPorPosicao(posicao);

        // Assert
        Assertions.assertEquals(3, peca.getValor());
    }

    @Test
    public void testGetPecaPorPosicaoPecaNaoEncontrada() {
        // Arrange
        int posicao = 15;

        // Act & Assert
        Assertions.assertThrows(JogoException.class, () -> tabuleiro.getPecaPorPosicao(posicao));
    }

    @Test
    public void testGetPecaPorValor() {
        // Arrange
        int valor = 3;

        // Act
        Peca peca = tabuleiro.getPecaPorValor(valor);

        // Assert
        Assertions.assertEquals(2, peca.getPosicao());
    }

    @Test
    public void testGetPecaPorValorPecaNaoEncontrada() {
        // Arrange
        int valor = 40;

        // Act & Assert
        Assertions.assertThrows(JogoException.class, () -> tabuleiro.getPecaPorValor(valor));
    }

    @Test
    public void testGetPecaVazia() {
        // Act
        Peca pecaVazia = tabuleiro.getPecaVazia();

        // Assert
        Assertions.assertTrue(pecaVazia.ehPecaVazia());
    }

    @Test
    public void testGetPecaVaziaPecaNaoEncontrada() {
        // Arrange
        List<Peca> pecasNaoVazias = new ArrayList<>();
        pecasNaoVazias.add(new Peca(1, 1));
        pecasNaoVazias.add(new Peca(2, 2));
        Tabuleiro t = new Tabuleiro(pecasNaoVazias);

        // Act & Assert
        Assertions.assertThrows(JogoException.class, t::getPecaVazia);
    }
}

