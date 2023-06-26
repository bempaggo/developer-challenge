package chat.gpt.gui;

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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = new JButton();
                botao.setFont(new Font("Arial", Font.BOLD, 36));
                botoes[i][j] = botao;
                add(botao);
            }
        }

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

        addKeyListener(this);
        setFocusable(true);
        atualizarTabuleiro();
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                jogo.mover(1, 0);
                break;
            case KeyEvent.VK_DOWN:
                jogo.mover(-1, 0);
                break;
            case KeyEvent.VK_LEFT:
                jogo.mover(0, 1);
                break;
            case KeyEvent.VK_RIGHT:
                jogo.mover(0, -1);
                break;
        }
        atualizarTabuleiro();
        if (jogo.jogoConcluido()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            jogo.reiniciarJogo();
            atualizarTabuleiro();
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
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
