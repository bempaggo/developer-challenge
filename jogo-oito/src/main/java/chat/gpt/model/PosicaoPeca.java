package chat.gpt.model;

public class PosicaoPeca {
    private final Integer linha;
    private final Integer coluna;

    public PosicaoPeca(Integer linha, Integer coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public Integer getLinha() {
        return this.linha;
    }

    public Integer getColuna() {
        return this.coluna;
    }

    public Integer pegaDistanciaLinhasPara(PosicaoPeca posicao) {
        return this.linha - posicao.linha;
    }

    public Integer pegaDistanciaColunasPara(PosicaoPeca posicao) {
        return this.coluna - posicao.coluna;
    }
}
