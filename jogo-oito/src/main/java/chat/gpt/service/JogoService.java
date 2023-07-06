package chat.gpt.service;

import javax.swing.*;

public interface JogoService {

    void gerarTabuleiro();

    void atualizarTabuleiro();

    boolean jogoConcluido();

    void reiniciarJogo();

    void mover(int posicao);

    JButton[] getBotoes();

    void setBotaoReiniciar(JButton botaoReiniciar);
}
