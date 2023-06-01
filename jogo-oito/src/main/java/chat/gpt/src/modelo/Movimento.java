package chat.gpt.src.modelo;

import java.util.Optional;

public enum Movimento {

    CIMA(-3), BAIXO(3), ESQUERDA(-1), DIREITA(1);

    final Integer mover;

    Movimento(Integer mover) {
        this.mover = mover;
    }

    public Integer getMover() {
        return mover;
    }

    public static boolean ehValido(Movimento movimento, Peca peca, Peca pecaVazia) {
        if (peca.ehPecaVazia()) return false;
        Integer novaPosicaoPecaVazia = pecaVazia.getPosicao() + movimento.mover;
        return novaPosicaoPecaVazia.equals(peca.getPosicao());
    }

    public static Optional<Movimento> obterMovimento(Integer mover) {
        for (Movimento movimento : Movimento.values()) {
            if (movimento.getMover().equals(mover)) {
                return Optional.of(movimento);
            }
        }
        return Optional.empty();
    }
}
