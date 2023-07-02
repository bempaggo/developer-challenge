package chat.gpt.model;

import chat.gpt.infra.contratos.TabuleiroLaco;
import chat.gpt.infra.util.Util;
import chat.gpt.infra.values.DirecaoMovimento;

public class Tabuleiro {
    private int[][] tabuleiro;

    public Tabuleiro() {
        this.resetaTabuleiro();
    }

    public void resetaTabuleiro() {
        this.tabuleiro = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    }

    public int pegaPecaPelaPosicao(PosicaoPeca posicaoPeca) {
        return this.tabuleiro[posicaoPeca.getLinha()][posicaoPeca.getColuna()];
    }

    public void executaParaCadaPosicao(TabuleiroLaco tabuleiroLaco) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                tabuleiroLaco.executar(linha, coluna);
            }
        }
    }

    public PosicaoPeca encontraPosicaoPecaPeloNumero(int numeroPeca) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[linha][coluna] == numeroPeca) {
                    return new PosicaoPeca(linha, coluna);
                }
            }
        }
        throw new RuntimeException("Posição não encontrada");
    }

    public PosicaoPeca pegaPosicaoVazia() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (this.tabuleiro[linha][coluna] == 0) {
                    return new PosicaoPeca(linha, coluna);
                }
            }
        }
        throw new RuntimeException("Ocorreu um erro no jogo");
    }

    public boolean movePeca(DirecaoMovimento direcao) {
        PosicaoPeca posicaoVazia = this.pegaPosicaoVazia();
        PosicaoPeca pNovaPosicao = this.pegaNovaPosicao(posicaoVazia, direcao);

        if (Util.posicaoEhInvalida(pNovaPosicao)) return false;

        this.tabuleiro[posicaoVazia.getLinha()][posicaoVazia.getColuna()]
                = this.tabuleiro[pNovaPosicao.getLinha()][pNovaPosicao.getColuna()];
        this.tabuleiro[pNovaPosicao.getLinha()][pNovaPosicao.getColuna()] = 0;
        return true;
    }

    private PosicaoPeca pegaNovaPosicao(PosicaoPeca pVazia, DirecaoMovimento direcao) {
        int novaLinha = pVazia.getLinha() + direcao.getLinha();
        int novaColuna = pVazia.getColuna() + direcao.getColuna();
        return new PosicaoPeca(novaLinha, novaColuna);
    }

    public boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] != count % 9) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }
}
