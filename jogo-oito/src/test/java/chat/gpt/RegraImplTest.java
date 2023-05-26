package chat.gpt;

import chat.gpt.src.Posicao;
import chat.gpt.src.RegraImpl;
import chat.gpt.src.Tabuleiro;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class RegraImplTest {

    private RegraImpl regra;
    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        regra = new RegraImpl();
        tabuleiro = new Tabuleiro(3);
    }

    @Test
    public void fazerMovimentoInvalidoTest() {
        Posicao posicaoInvalida = new Posicao(0, 0);
        boolean movimentoFeito = regra.fazerMovimento(tabuleiro, posicaoInvalida);
        Assert.assertFalse(movimentoFeito);

        posicaoInvalida = new Posicao(1, 1);
        movimentoFeito = regra.fazerMovimento(tabuleiro, posicaoInvalida);
        Assert.assertFalse(movimentoFeito);
    }

    @Test
    public void fazerMovimentoPecaValidaTest() {

        Posicao posicaoAcimaPecaVazia = new Posicao(1, 2);
        boolean movimento1 = regra.fazerMovimento(tabuleiro, posicaoAcimaPecaVazia);
        Assert.assertTrue(movimento1);

        posicaoAcimaPecaVazia = new Posicao(0, 2);
        boolean movimento2 = regra.fazerMovimento(tabuleiro, posicaoAcimaPecaVazia);
        Assert.assertTrue(movimento2);

        Posicao posicaoEsquerdaPecaVazia = new Posicao(0, 1);
        boolean movimento3 = regra.fazerMovimento(tabuleiro, posicaoEsquerdaPecaVazia);
        Assert.assertTrue(movimento3);

        Posicao posicaoDireitaPecaVazia = new Posicao(0, 2);
        boolean movimento4 = regra.fazerMovimento(tabuleiro, posicaoDireitaPecaVazia);
        Assert.assertTrue(movimento4);

        Posicao posicaoAbaixoPecaVazia = new Posicao(1, 2);
        boolean movimento5 = regra.fazerMovimento(tabuleiro, posicaoAbaixoPecaVazia);
        Assert.assertTrue(movimento5);
    }

    @Test
    public void verificarVitoriaTest() {
        regra.embaralharPecas(tabuleiro);
        Assert.assertFalse(regra.verificarVitoria(tabuleiro));

        regra.reiniciarJogo(tabuleiro);
        Assert.assertTrue(regra.verificarVitoria(tabuleiro));
    }
}


