package chat.gpt.infra.values;

import chat.gpt.model.PosicaoPeca;

import java.util.Arrays;

public enum DirecaoMovimento {
    CIMA(1, 0),
    BAIXO(-1, 0),
    ESQUERDA(0, 1),
    DIREITA(0, -1),
    SEM_MOVIMENTO(0, 0);

    private final int deslocamentoHorizontal;
    private final int deslocamentoVertical;

    DirecaoMovimento(int deslocamentoHorizontal, int deslocamentoVertical) {
        this.deslocamentoHorizontal = deslocamentoHorizontal;
        this.deslocamentoVertical = deslocamentoVertical;
    }

    public int getDeslocamentoHorizontal() {
        return deslocamentoHorizontal;
    }

    public int getDeslocamentoVertical() {
        return deslocamentoVertical;
    }

    public static DirecaoMovimento pegaPelaPosicaoPecaTocada(PosicaoPeca pVazia, PosicaoPeca pPecaClicada) {
        int linha = pPecaClicada.pegaDistanciaLinhasPara(pVazia);
        int coluna = pPecaClicada.pegaDistanciaColunasPara(pVazia);
        return Arrays.stream(values())
                .filter(movimentoMouseValue -> movimentoMouseValue.deslocamentoHorizontal == linha &&
                        movimentoMouseValue.deslocamentoVertical == coluna)
                .findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);
    }

    public static DirecaoMovimento pegaPeloTeclado(int teclaCode) {
        return Arrays.stream(DirecaoMovimentoTeclado.values())
                .filter(movimentoTeclado -> movimentoTeclado.getTeclaCode() == teclaCode)
                .map(DirecaoMovimentoTeclado::getMovimento)
                .findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);
    }
}
