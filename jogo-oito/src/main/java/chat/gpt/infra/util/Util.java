package chat.gpt.infra.util;

import chat.gpt.infra.values.DirecaoMovimento;
import chat.gpt.model.PosicaoPeca;

public class Util {

    public static boolean movimentoEhInvalido(DirecaoMovimento pMovimento) {
        return pMovimento.getColuna() == -1 && pMovimento.getLinha() == -1;
    }

    public static boolean posicaoEhInvalida(PosicaoPeca pMovimento) {
        return pMovimento.getLinha() < 0 || pMovimento.getLinha() > 2
                || pMovimento.getColuna() < 0 || pMovimento.getColuna() > 2;
    }
}
