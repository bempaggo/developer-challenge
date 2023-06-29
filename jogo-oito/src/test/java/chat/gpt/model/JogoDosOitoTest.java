package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chat.gpt.exception.MovimentoInvalidoException;

public class JogoDosOitoTest {

    private JogoDosOito jogo;

    @BeforeEach
    public void setUp() {
        jogo = new JogoDosOito();
    }

    @Test
    public void testConstrutor() {
        JogoDosOito jogo = new JogoDosOito();

        // Verificar se o tabuleiro foi inicializado corretamente no construtor
        int[][] tabuleiro = jogo.estadoAtualTabuleiro();
        int boardLength = tabuleiro.length;
        int boardWidth = tabuleiro[0].length;

        Assertions.assertEquals(3, boardLength);
        Assertions.assertEquals(3, boardWidth);

        // Verificar se as posições estão preenchidas corretamente
        int expectedValue = 1;
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (i == boardLength - 1 && j == boardWidth - 1) {
                    Assertions.assertEquals(0, tabuleiro[i][j]);
                } else {
                    Assertions.assertEquals(expectedValue, tabuleiro[i][j]);
                    expectedValue++;
                }
            }
        }
    }


    @Test
    public void testMoverComMovimentoInvalido() {
        // Movimento inválido: mover para uma posição fora do tabuleiro
        int[] deslocamento = { 1, 0 }; // Movimento para baixo (fora do tabuleiro)

        // Verificar se a exceção MovimentoInvalidoException é lançada
        Assertions.assertThrows(MovimentoInvalidoException.class, () -> jogo.mover(deslocamento));
    }

    @Test
    public void testJogoConcluido() {
        // Verificar se o jogo é concluído corretamente
        int[][] tabuleiroFinal = jogo.estadoAtualTabuleiro();

        // Preencher o tabuleiro com a configuração final do jogo
        int[][] JOGO_CONCLUIDO = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };
        for (int i = 0; i < JOGO_CONCLUIDO.length; i++) {
            System.arraycopy(JOGO_CONCLUIDO[i], 0, tabuleiroFinal[i], 0, JOGO_CONCLUIDO[i].length);
        }

        // Verificar se o jogo é considerado concluído
        Assertions.assertTrue(jogo.jogoConcluido());
    }
   

}
