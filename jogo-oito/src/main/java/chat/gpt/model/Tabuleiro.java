package chat.gpt.model;

import chat.gpt.infra.contratos.TabuleiroLaco;
import chat.gpt.infra.util.Util;
import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.infra.values.TabuleiroPecas;

import java.util.*;

public class Tabuleiro {
    private List<List<Peca>> tabuleiro;

    public Tabuleiro() {
        this.criaTabuleiro();
    }

    private void criaTabuleiro() {
        this.tabuleiro = TabuleiroPecas.pegaPecasTabuleiro();
    }

    public void resetaTabuleiro() {
        this.tabuleiro.clear();
        this.criaTabuleiro();
    }


    public Peca pegaPecaPelaPosicao(PosicaoPeca posicaoPeca) {
        return this.tabuleiro.get(posicaoPeca.getLinha()).get(posicaoPeca.getColuna());
    }

    public Peca pegaPecaPelaPosicao(Integer linha, Integer coluna) {
        return this.pegaPecaPelaPosicao(new PosicaoPeca(linha, coluna));
    }

    private void mudaPosicaoPeca(PosicaoPeca posicaoAlvo, Peca peca) {
        this.tabuleiro.get(posicaoAlvo.getLinha())
                .set(posicaoAlvo.getColuna(), peca);
    }

    public void executaParaCadaPosicaoTabuleiro(TabuleiroLaco tabuleiroLaco) {
        for (Integer linha = 0; linha < 3; linha++) {
            for (Integer coluna = 0; coluna < 3; coluna++) {
                tabuleiroLaco.executar(linha, coluna);
            }
        }
    }

    public PosicaoPeca encontraPosicaoPeca(Peca peca) {
        for (Integer linha = 0; linha < 3; linha++) {
            for (Integer coluna = 0; coluna < 3; coluna++) {
                if (this.pegaPecaPelaPosicao(linha, coluna).getValor().equals(peca.getValor())) {
                    return new PosicaoPeca(linha, coluna);
                }
            }
        }
        throw new RuntimeException("Posição não encontrada");
    }

    public PosicaoPeca pegaPosicaoVazia() {
        for (Integer linha = 0; linha < 3; linha++) {
            for (Integer coluna = 0; coluna < 3; coluna++) {
                if (this.pegaPecaPelaPosicao(linha, coluna).getValor() == 0) {
                    return new PosicaoPeca(linha, coluna);
                }
            }
        }
        throw new RuntimeException("Ocorreu um erro no jogo");
    }

    public Boolean movePeca(DirecaoMovimento direcao) {
        PosicaoPeca posicaoVazia = this.pegaPosicaoVazia();
        PosicaoPeca posicaoNova = this.pegaNovaPosicao(posicaoVazia, direcao);

        if (Util.posicaoEhInvalida(posicaoNova)) return false;

        this.mudaPosicaoPeca(posicaoVazia,
                this.pegaPecaPelaPosicao(posicaoNova));
        this.mudaPosicaoPeca(posicaoNova, new Peca(0));
        return true;
    }

    private PosicaoPeca pegaNovaPosicao(PosicaoPeca pVazia, DirecaoMovimento direcao) {
        Integer novaLinha = pVazia.getLinha() + direcao.getDeslocamentoHorizontal();
        Integer novaColuna = pVazia.getColuna() + direcao.getDeslocamentoVertical();
        return new PosicaoPeca(novaLinha, novaColuna);
    }

    public Boolean jogoConcluido() {
        Integer count = 1;
        for (Integer linha = 0; linha < 3; linha++) {
            for (Integer coluna = 0; coluna < 3; coluna++) {
                if (!this.pegaPecaPelaPosicao(linha, coluna).getValor().equals(count % 9)) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }
}
