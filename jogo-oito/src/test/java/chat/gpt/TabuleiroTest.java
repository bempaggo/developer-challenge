package chat.gpt;

import chat.gpt.src.Posicao;
import chat.gpt.src.Tabuleiro;
import chat.gpt.src.exception.JogoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @Before
    public void setUp() {
        tabuleiro = new Tabuleiro(3);
    }

    @Test
    public void dimensaoTabuleiroTest() {
        Assert.assertEquals(3, tabuleiro.getDimensao().intValue());
        Assert.assertEquals(4, new Tabuleiro(4).getDimensao().intValue());
        Assert.assertEquals(10, new Tabuleiro(10).getDimensao().intValue());
    }

    @Test
    public void dimensaoInvalidaTabuleiroTest() {
        Assert.assertThrows(JogoException.class, () -> new Tabuleiro(1));
        Assert.assertThrows(JogoException.class, () -> new Tabuleiro(-1));
    }

    @Test
    public void valorPecaVaziaTest() {
        Assert.assertEquals(9, tabuleiro.getValorPecaVazia().intValue());
        Assert.assertEquals(25, new Tabuleiro(5).getValorPecaVazia().intValue());
        Assert.assertEquals(100, new Tabuleiro(10).getValorPecaVazia().intValue());
    }

    @Test
    public void inicializarTabuleiroPosicaoEPecaTest() {
        int valorPeca = 1;
        for (int i = 0; i < tabuleiro.getDimensao(); i++) {
            for (int j = 0; j < tabuleiro.getDimensao(); j++) {
                Posicao posicao = new Posicao(i, j);
                Assert.assertTrue(tabuleiro.getTabuleiro().containsKey(posicao));
                Assert.assertEquals(valorPeca, tabuleiro.getPeca(posicao).getValor());
                valorPeca++;
            }
        }
    }

    @Test
    public void testReiniciarTabuleiro() {
        tabuleiro.reiniciarTabuleiro();
        inicializarTabuleiroPosicaoEPecaTest();
    }

    @Test
    public void testEmbaralharPecas() {
        tabuleiro.embaralharPecas();

        Assert.assertEquals(3, tabuleiro.getDimensao().intValue());
        Assert.assertEquals(9, tabuleiro.getTabuleiro().size());

        // Verifica se as peças estão embaralhadas
        boolean isEmbaralhado = false;
        int valorPeca = 1;
        for (int i = 0; i < tabuleiro.getDimensao(); i++) {
            for (int j = 0; j < tabuleiro.getDimensao(); j++) {
                Posicao posicao = new Posicao(i, j);
                // Verifica se posicao existe no tabuleiro
                Assert.assertTrue(tabuleiro.getTabuleiro().containsKey(posicao));
                // Verifica se o valor da peca na posicao eh o mesmo do valor padrao (ordenado)
                if (tabuleiro.getPeca(posicao).getValor() != valorPeca) {
                    isEmbaralhado = true;
                    break;
                }
            }
            if (isEmbaralhado) {
                break;
            }
        }
        Assert.assertTrue(isEmbaralhado);
    }

    @Test
    public void testEqualsAndHashCode() {

        // Teste de igualdade com o mesmo objeto
        Assert.assertEquals(tabuleiro, tabuleiro);
        Assert.assertEquals(tabuleiro.hashCode(), tabuleiro.hashCode());

        // Teste de igualdade com objeto nulo
        Assert.assertNotEquals(null, tabuleiro);

        // Teste de igualdade com outro objeto do mesmo tipo e mesmo estado
        Tabuleiro outroTabuleiro = new Tabuleiro(3);
        Assert.assertEquals(tabuleiro, outroTabuleiro);
        Assert.assertEquals(tabuleiro.hashCode(), outroTabuleiro.hashCode());

        // Teste de igualdade com outro objeto do mesmo tipo e estado diferente
        outroTabuleiro.embaralharPecas();
        Assert.assertNotEquals(tabuleiro, outroTabuleiro);

    }


}
