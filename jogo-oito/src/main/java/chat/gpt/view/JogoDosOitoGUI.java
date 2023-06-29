package chat.gpt.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

import chat.gpt.controller.BotaoReiniciarListener;
import chat.gpt.controller.TecladoInputListener;
import chat.gpt.exception.MovimentoInvalidoException;
import chat.gpt.exception.TeclaInvalidaException;
import chat.gpt.model.Botao;
import chat.gpt.model.BotaoReiniciar;
import chat.gpt.model.JogoDosOito;

import static chat.gpt.view.Constantes.*;

public class JogoDosOitoGUI extends JFrame implements TecladoInputListener, BotaoReiniciarListener {

    private JogoDosOito jogo;
    private Botao[][] botoes = new Botao[3][3];

    public JogoDosOitoGUI() {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        jogo = new JogoDosOito();
        criarBotoes();
        criarBotaoReiniciar();
        configurarJanela();

        setVisible(true);
        requestFocus();
    }

    private void criarBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Botao botao = Botao.criarBotaoVazio();
                botoes[i][j] = botao;
                add(botao);
            }
        }
    }

    private void criarBotaoReiniciar() {
        BotaoReiniciar botaoReiniciar = new BotaoReiniciar(this);

        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));
    }

    private void configurarJanela() {
        addKeyListener(this);
        setFocusable(true);
        atualizarTabuleiro();
    }
    
    @Override
    public void processarInput(int[] input) {
        try {
            jogo.mover(input);
            atualizarTabuleiro();
            if (jogo.jogoConcluido()) {
                exibirMensagem("Parabéns, você venceu!");
            }
        } catch (MovimentoInvalidoException error) {
            exibirMensagem(error.getMessage());
        } catch (TeclaInvalidaException error) {
            exibirMensagem("Tecla inválida");           
        } 
    }

    private void atualizarTabuleiro() {
        int[][] tabuleiro = jogo.estadoAtualTabuleiro();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == VAZIO) {
                    botoes[i][j].setText("");
                } else {
                    botoes[i][j].setText(String.valueOf(tabuleiro[i][j]));
                }
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void reiniciarJogo() {
        jogo.reiniciarJogo();
        atualizarTabuleiro();
    }
}
