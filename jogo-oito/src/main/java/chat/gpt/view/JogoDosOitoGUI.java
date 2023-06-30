package chat.gpt.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

import chat.gpt.controller.JogoDosOitoService;
import chat.gpt.model.Botao;
import chat.gpt.model.BotaoReiniciar;
import chat.gpt.model.JogoDosOito;

import static chat.gpt.view.Constantes.*;

public class JogoDosOitoGUI extends JFrame {

    private JogoDosOito jogo;
    private JogoDosOitoService jogoService;
    private Botao[][] botoes = new Botao[3][3];

    public JogoDosOitoGUI() {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        jogo = new JogoDosOito();
        jogoService = new JogoDosOitoService(jogo, this);
        criarBotoes();
        criarBotaoReiniciar();
        configurarJanela();

        setVisible(true);
        requestFocus();
    }

    private void configurarJanela() {
        addKeyListener(jogoService);
        setFocusable(true);
        atualizarTabuleiro();
    }
    
    public void atualizarTabuleiro() {
        int[][] tabuleiro = jogo.estadoAtualTabuleiro();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (tabuleiro[i][j] == VAZIO) {
                    botoes[i][j].setText("");
                } else {
                    botoes[i][j].setText(String.valueOf(tabuleiro[i][j]));
                }
            }
        }
    }

    private void criarBotoes() {
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardWidth; j++) {
                Botao botao = Botao.criarBotaoVazio();
                botoes[i][j] = botao;
                add(botao);
            }
        }
    }

    private void criarBotaoReiniciar() {
        add(new JLabel(""));
        add(new BotaoReiniciar(jogoService));
        add(new JLabel(""));
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

}
