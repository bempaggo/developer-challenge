package chat.gpt.src.servico;

import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;

public interface Regra {

    Boolean verificarVitoria(Tabuleiro tabuleiro);
    Boolean validarMovimento(Peca pecaMovida, Peca pecaVazia);
}
