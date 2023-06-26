package tests;

import chat.gpt.model.TabuleiroDoJogo;
import chat.gpt.controller.LogicaDoJogo;
import chat.gpt.view.JogoDosOito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicaDoJogoTest {

    private TabuleiroDoJogo tabuleiroDoJogo;
    private LogicaDoJogo logicaDoJogo;

    @BeforeEach
    public void setup() {
        JogoDosOito jogoDosOito = new JogoDosOito();
        tabuleiroDoJogo = new TabuleiroDoJogo(jogoDosOito);
        logicaDoJogo = new LogicaDoJogo(tabuleiroDoJogo);
    }


    @Test
    public void testVerificarJogoConcluido() {
        int[][] tabuleiro = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        tabuleiroDoJogo.setTabuleiro(tabuleiro);

        // Tabuleiro completo - jogo concluído
        assertTrue(logicaDoJogo.verificarJogoConcluido());

        // Tabuleiro incompleto - jogo não concluído
        tabuleiro[1][1] = 0;
        assertFalse(logicaDoJogo.verificarJogoConcluido());

        // Tabuleiro completo novamente - jogo concluído
        tabuleiro = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        tabuleiroDoJogo.setTabuleiro(tabuleiro);
        assertTrue(logicaDoJogo.verificarJogoConcluido());

    }

}
