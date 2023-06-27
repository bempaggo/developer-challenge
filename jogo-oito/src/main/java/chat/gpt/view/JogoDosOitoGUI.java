package chat.gpt.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;

import chat.gpt.controller.TecladoInputListener;
import chat.gpt.model.JogoDosOito;

import static chat.gpt.view.Constantes.*;

public class JogoDosOitoGUI extends JFrame implements TecladoInputListener {

    private JogoDosOito jogo;
    private JButton[][] botoes = new JButton[3][3];
    private JButton botaoReiniciar;

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
    }

    private void criarBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = new JButton();
                botao.setFont(new Font("Arial", Font.BOLD, 36));
                botoes[i][j] = botao;
                add(botao);
            }
        }
    }

    private void criarBotaoReiniciar() {
        botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(e -> reiniciarJogo());
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
        jogo.mover(input);
        atualizarTabuleiro();
        if (jogo.jogoConcluido()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
        }
    }

    private void atualizarTabuleiro() {
        int[][] tabuleiro = jogo.getTabuleiro();

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

    private void reiniciarJogo() {
        jogo.reiniciarJogo();
        atualizarTabuleiro();
    }

}
