package chat.gpt.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import chat.gpt.model.JogoDosOito;
import static chat.gpt.view.Constantes.*;

public class JogoDosOitoGUI extends JFrame implements KeyListener {

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
        botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogo.reiniciarJogo();
                atualizarTabuleiro();
            }
        });
        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));
    }

    private void configurarJanela() {
        addKeyListener(this);
        setFocusable(true);
        atualizarTabuleiro();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                moverPeca(MOVE_UP);
                break;
            case KeyEvent.VK_DOWN:
                moverPeca(MOVE_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                moverPeca(MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                moverPeca(MOVE_RIGHT);
                break;
        }
        atualizarTabuleiro();
        if (jogo.jogoConcluido()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    private void moverPeca(int[] deslocamento) {
        int linha = deslocamento[0];
        int coluna = deslocamento[1];
        jogo.mover(linha, coluna);
    }

    private void atualizarTabuleiro() {
        int[][] tabuleiro = jogo.getTabuleiro();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = botoes[i][j];
                int valor = tabuleiro[i][j];
                if (valor == 0) {
                    botao.setText("");
                } else {
                    botao.setText(String.valueOf(valor));
                }
            }
        }
    }
}
