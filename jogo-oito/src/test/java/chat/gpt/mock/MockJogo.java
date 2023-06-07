package chat.gpt.mock;

import chat.gpt.src.servico.Jogo;
import chat.gpt.src.servico.Regra;

public class MockJogo extends Jogo {
    private boolean embaralharPecasChamado;
    private boolean reiniciarChamado;

    public MockJogo(Regra regra) {
        super(regra);
    }

    @Override
    public void embaralharPecas() {
        embaralharPecasChamado = true;
    }

    @Override
    public void reiniciar() {
        reiniciarChamado = true;
    }

    public boolean isEmbaralharPecasChamado() {
        return embaralharPecasChamado;
    }

    public boolean isReiniciarChamado() {
        return reiniciarChamado;
    }
}