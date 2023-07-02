import chat.gpt.infra.util.Util;
import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.model.PosicaoPeca;
import chat.gpt.model.Tabuleiro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

class TabuleiroTest {
    private Tabuleiro tabuleiro;

    @BeforeEach
    public void inicializar() {
       this.tabuleiro = new Tabuleiro();
    }

    @Test
    void deveriaPegarPosicaoVazia() {
        Assertions.assertDoesNotThrow(() -> this.tabuleiro.pegaPosicaoVazia());
    }

    @Test
    void deveriaPegarPosicaoPecasExistentes() {
        for (int i = 1; i < 9; i++) {
            int numeroPeca = i;
            Assertions.assertDoesNotThrow(() -> this.tabuleiro.encontraPosicaoPecaPeloNumero(numeroPeca));
        }
    }

    @Test
    void naoDeveriaPegarPosicaoDePecasInexistentes() {
        for (int i = 9; i < 99; i++) {
            int numeroPeca = i;
            Assertions.assertThrows(RuntimeException.class, () -> this.tabuleiro.encontraPosicaoPecaPeloNumero(numeroPeca));
        }
    }

    @Test
    void naoDeveriaAceitarMovimentosInvalidos() {
        boolean movimentoMouseInvalido = Util.movimentoEhInvalido(DirecaoMovimento.SEM_MOVIMENTO);
        Assertions.assertTrue(movimentoMouseInvalido);
    }

    @Test
    void naoDeveriaAceitarPosicoesPecasInvalidas() {
        Assertions.assertTrue(Util.posicaoEhInvalida(new PosicaoPeca(5, 5)));
        Assertions.assertTrue(Util.posicaoEhInvalida(new PosicaoPeca(-5, -5)));
        Assertions.assertTrue(Util.posicaoEhInvalida(new PosicaoPeca(15, 15)));
    }

    @Test
    void deveriaTerMovimentoComTeclasValidas() {
        DirecaoMovimento movimentoInvalido1 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_UP);
        Assertions.assertEquals(movimentoInvalido1, DirecaoMovimento.CIMA);

        DirecaoMovimento movimentoInvalido2 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_DOWN);
        Assertions.assertEquals(movimentoInvalido2, DirecaoMovimento.BAIXO);

        DirecaoMovimento movimentoInvalido3 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_LEFT);
        Assertions.assertEquals(movimentoInvalido3, DirecaoMovimento.ESQUERDA);

        DirecaoMovimento movimentoInvalido4 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_RIGHT);
        Assertions.assertEquals(movimentoInvalido4, DirecaoMovimento.DIREITA);
    }


    @Test
    void naoDeveriaTerMovimentoComTeclasInvalidas() {
        DirecaoMovimento movimentoInvalido1 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_E);
        Assertions.assertEquals(movimentoInvalido1, DirecaoMovimento.SEM_MOVIMENTO);

        DirecaoMovimento movimentoInvalido2 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_L);
        Assertions.assertEquals(movimentoInvalido2, DirecaoMovimento.SEM_MOVIMENTO);

        DirecaoMovimento movimentoInvalido3 = DirecaoMovimento.pegaPeloTeclado(KeyEvent.VK_U);
        Assertions.assertEquals(movimentoInvalido3, DirecaoMovimento.SEM_MOVIMENTO);
    }

    @Test
    @DisplayName("Teste da movimentação de pecas com sequencia de movimentos iniciais validos.")
    void deveriaMoverPecasSeDirecaoForInvalida() {
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.DIREITA));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.DIREITA));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.BAIXO));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.BAIXO));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.ESQUERDA));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.ESQUERDA));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.CIMA));
        Assertions.assertTrue(this.tabuleiro.movePeca(DirecaoMovimento.CIMA));
    }

    @Test
    @DisplayName("Teste da movimentação de pecas com sequencia de movimentos iniciais invalidos.")
    void naoDeveriaMoverPecasSeDirecaoForInvalida() {
        Assertions.assertFalse(this.tabuleiro.movePeca(DirecaoMovimento.ESQUERDA));
        Assertions.assertFalse(this.tabuleiro.movePeca(DirecaoMovimento.CIMA));
    }
}
