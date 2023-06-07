package chat.gpt.service;

import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;
import chat.gpt.src.servico.RegraImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RegraImplTest {

    RegraImpl regra = new RegraImpl();

    @Test
    public void testVerificarVitoriaTrue() {
        // Arrange
        List<Peca> pecas = new ArrayList<>();
        IntStream.range(0, 9).forEach(i -> pecas.add(new Peca(i + 1, i)));
        Tabuleiro tabuleiroVitoria = new Tabuleiro(pecas);

        // Act
        Boolean vitoria = regra.verificarVitoria(tabuleiroVitoria);

        // Assert
        Assertions.assertTrue(vitoria);
    }

    @Test
    public void testVerificarVitoriaFalse() {
        // Arrange
        Tabuleiro tabuleiroNaoVitoria = new Tabuleiro(List.of(
                new Peca(1, 0),
                new Peca(2, 1),
                new Peca(3, 2),
                new Peca(4, 3),
                new Peca(5, 4),
                new Peca(6, 5),
                new Peca(7, 6),
                new Peca(9, 7),
                new Peca(8, 8)
        ));

        // Act
        Boolean vitoria = regra.verificarVitoria(tabuleiroNaoVitoria);

        // Assert
        Assertions.assertFalse(vitoria);
    }

    @Test
    public void testValidarMovimentoValido() {
        // Arrange
        Peca pecaMovida = new Peca(8, 7);
        Peca pecaVazia = new Peca(9, 8);
        RegraImpl regra = new RegraImpl();

        // Act
        Boolean movimentoValido = regra.validarMovimento(pecaMovida, pecaVazia);

        // Assert
        Assertions.assertTrue(movimentoValido);
    }

    @Test
    public void testValidarMovimentoInvalido() {
        // Arrange
        Peca pecaMovida = new Peca(8, 2);
        Peca pecaVazia = new Peca(9, 8);

        // Act
        Boolean movimentoValido = regra.validarMovimento(pecaMovida, pecaVazia);

        // Assert
        Assertions.assertFalse(movimentoValido);
    }
}

