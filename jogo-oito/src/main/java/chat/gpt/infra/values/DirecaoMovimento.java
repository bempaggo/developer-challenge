package chat.gpt.infra.values;

import chat.gpt.model.PosicaoPeca;

import java.util.Arrays;

public enum DirecaoMovimento {
    CIMA(1, 0),
    BAIXO(-1, 0),
    ESQUERDA(0, 1),
    DIREITA(0, -1),
    SEM_MOVIMENTO(-1, -1);

    private final int linha;
    private final int coluna;
    DirecaoMovimento(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public PosicaoPeca getPosicao() {
        return new PosicaoPeca(this.linha, this.coluna);
    }

    public static DirecaoMovimento pegaPelaPosicaoBotaoTocado(PosicaoPeca pVazia, PosicaoPeca pPecaClicada) {
        int linha = pPecaClicada.pegarDiferencaLinhaPara(pVazia);
        int coluna = pPecaClicada.pegarDiferencaColunaPara(pVazia);
        return Arrays.stream(values()).filter(movimentoMouseValue -> movimentoMouseValue.linha == linha &&
                movimentoMouseValue.coluna == coluna).findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);
    }

    public static DirecaoMovimento pegaPeloTeclado(int teclaCode) {
        return Arrays.stream(DirecaoMovimentoTeclado.values()).filter(movimentoTeclado -> movimentoTeclado.getTeclaCode() == teclaCode)
                .map(DirecaoMovimentoTeclado::getMovimento)
                .findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);

    }
}
