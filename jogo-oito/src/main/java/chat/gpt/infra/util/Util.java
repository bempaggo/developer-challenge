package chat.gpt.infra.util;

import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.model.PosicaoPeca;

public class Util {

    public static boolean movimentoEhInvalido(DirecaoMovimento pMovimento) {
        DirecaoMovimento semMovimento = DirecaoMovimento.SEM_MOVIMENTO;
        return pMovimento.getDeslocamentoVertical() == semMovimento.getDeslocamentoVertical()
                && pMovimento.getDeslocamentoHorizontal() == semMovimento.getDeslocamentoHorizontal();
    }

    public static boolean posicaoEhInvalida(PosicaoPeca pMovimento) {
        return pMovimento.getLinha() < 0 || pMovimento.getLinha() > 2
                || pMovimento.getColuna() < 0 || pMovimento.getColuna() > 2;
    }
}
