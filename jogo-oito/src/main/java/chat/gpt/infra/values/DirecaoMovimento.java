package chat.gpt.infra.values;

import chat.gpt.model.PosicaoPeca;

import java.util.Arrays;

public enum DirecaoMovimento {
    CIMA(1, 0),
    BAIXO(-1, 0),
    ESQUERDA(0, 1),
    DIREITA(0, -1),
    SEM_MOVIMENTO(0, 0);

    private final Integer deslocamentoHorizontal;
    private final Integer deslocamentoVertical;

    DirecaoMovimento(Integer deslocamentoHorizontal, Integer deslocamentoVertical) {
        this.deslocamentoHorizontal = deslocamentoHorizontal;
        this.deslocamentoVertical = deslocamentoVertical;
    }

    public Integer getDeslocamentoHorizontal() {
        return deslocamentoHorizontal;
    }

    public Integer getDeslocamentoVertical() {
        return deslocamentoVertical;
    }

    public static DirecaoMovimento pegaPelaPosicaoPecaClicada(PosicaoPeca pVazia, PosicaoPeca pPecaClicada) {
        Integer linha = pPecaClicada.pegaDistanciaLinhasPara(pVazia);
        Integer coluna = pPecaClicada.pegaDistanciaColunasPara(pVazia);
        return Arrays.stream(values())
                .filter(movimentoMouseValue -> movimentoMouseValue.deslocamentoHorizontal.equals(linha) &&
                        movimentoMouseValue.deslocamentoVertical.equals(coluna))
                .findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);
    }

    public static DirecaoMovimento pegaPeloTeclado(Integer teclaCode) {
        return Arrays.stream(DirecaoMovimentoTeclado.values())
                .filter(movimentoTeclado -> movimentoTeclado.getTeclaCode().equals(teclaCode))
                .map(DirecaoMovimentoTeclado::getMovimento)
                .findFirst().orElse(DirecaoMovimento.SEM_MOVIMENTO);
    }
}
