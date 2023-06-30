package chat.gpt.controller;

import chat.gpt.exception.MovimentoInvalidoException;
import chat.gpt.exception.TeclaInvalidaException;
import chat.gpt.model.JogoDosOito;
import chat.gpt.view.JogoDosOitoGUI;

public class JogoDosOitoService implements TecladoInputListener, BotaoReiniciarListener {

    private JogoDosOito jogo;
    private JogoDosOitoGUI view;

    public JogoDosOitoService(JogoDosOito jogo, JogoDosOitoGUI view) {
        this.jogo = jogo;
        this.view = view;
    }

    @Override
    public void reiniciarJogo() {
        jogo.reiniciarJogo();
        view.atualizarTabuleiro();
    }
    @Override
    public void processarInput(int[] input) {
        try {
            jogo.mover(input);
            view.atualizarTabuleiro();
            if (jogo.jogoConcluido()) {
                view.exibirMensagem("Parabéns, você venceu!");
            }
        } catch (MovimentoInvalidoException error) {
            view.exibirMensagem(error.getMessage());
        } catch (TeclaInvalidaException error) {
            view.exibirMensagem(error.getMessage());           
        } 
    }

}