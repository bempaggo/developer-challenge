package tests;

import chat.gpt.model.TabuleiroDoJogo;
import chat.gpt.view.JogoDosOito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroDoJogoTest {

    private TabuleiroDoJogo tabuleiroDoJogo;

    @BeforeEach
    public void setUp() {
        JogoDosOito jogo = new JogoDosOito();
        tabuleiroDoJogo = new TabuleiroDoJogo(jogo);
    }

    @Test
    public void testGetTabuleiro() {
        int[][] tabuleiro = tabuleiroDoJogo.getTabuleiro();
        assertNotNull(tabuleiro);
        assertEquals(3, tabuleiro.length);
        assertEquals(3, tabuleiro[0].length);
        assertEquals(3, tabuleiro[1].length);
        assertEquals(3, tabuleiro[2].length);
    }

    @Test
    public void testSetTabuleiro() {
        int[][] tabuleiro = {{3, 2, 1}, {4, 5, 6}, {7, 8, 0}};
        tabuleiroDoJogo.setTabuleiro(tabuleiro);
        assertArrayEquals(tabuleiro, new int[][]{{3, 2, 1}, {4, 5, 6}, {7, 8, 0}});
    }

    @Test
    public void testGetBotoes() {
        JButton[][] botoes = tabuleiroDoJogo.getBotoes();
        assertNotNull(botoes);
        assertEquals(3, botoes.length);
        assertEquals(3, botoes[0].length);
    }

    @Test
    public void testGetJogo() {
        JogoDosOito jogo = tabuleiroDoJogo.getJogo();
        assertNotNull(jogo);
    }

    @Test
    public void testGetValorCelula() {
        int[][] tabuleiro = {{3, 2, 1}, {4, 5, 6}, {7, 8, 0}};
        tabuleiroDoJogo.setTabuleiro(tabuleiro);
        int valor = tabuleiroDoJogo.getValorCelula(1, 2);
        assertEquals(6, valor);
        int valor2 = tabuleiroDoJogo.getValorCelula(2, 1);
        assertEquals(8, valor2);
    }

    @Test
    public void testSetValorCelula() {
        tabuleiroDoJogo.setValorCelula(2, 1, 9);
        assertEquals(9, tabuleiroDoJogo.getValorCelula(2, 1));
    }

    @Test
    public void testInicializarBotoes() {
        JButton[][] botoes = tabuleiroDoJogo.getBotoes();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertNotNull(botoes[i][j]);
            }
        }
    }

    @Test
    public void testReiniciarJogo() {
        int[][] tabuleiroAntes = {{3, 2, 1}, {4, 5, 6}, {7, 8, 0}};

        tabuleiroDoJogo.reiniciarJogo();

        int[][] tabuleiroDepois = tabuleiroDoJogo.getTabuleiro();
        assertNotEquals(tabuleiroAntes, tabuleiroDepois);
    }
}
