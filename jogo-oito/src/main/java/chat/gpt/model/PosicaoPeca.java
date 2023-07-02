package chat.gpt.model;

public class PosicaoPeca {
    private final int linha;
    private final int coluna;

    public PosicaoPeca(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return this.linha;
    }

    public int getColuna() {
        return this.coluna;
    }

    public int pegaDistanciaLinhasPara(PosicaoPeca posicao) {
        return this.linha - posicao.linha;
    }

    public int pegaDistanciaColunasPara(PosicaoPeca posicao) {
        return this.coluna - posicao.coluna;
    }
}
