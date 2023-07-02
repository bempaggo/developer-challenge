package chat.gpt.infra.values;

import java.awt.event.KeyEvent;

public enum DirecaoMovimentoTeclado {
    KEY_UP(KeyEvent.VK_UP, DirecaoMovimento.CIMA),
    KEY_DOWN(KeyEvent.VK_DOWN, DirecaoMovimento.BAIXO),
    KEY_LEFT(KeyEvent.VK_LEFT, DirecaoMovimento.ESQUERDA),
    KEY_RIGHT(KeyEvent.VK_RIGHT, DirecaoMovimento.DIREITA);


    private final DirecaoMovimento movimento;
    private final int teclaCode;

    DirecaoMovimentoTeclado(int teclaCode, DirecaoMovimento movimento) {
        this.teclaCode = teclaCode;
        this.movimento = movimento;
    }

    public DirecaoMovimento getMovimento() {
        return movimento;
    }

    public int getTeclaCode() {
        return teclaCode;
    }
}
