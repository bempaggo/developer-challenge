package chat.gpt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JogoDosOito extends JFrame implements KeyListener {
    private JButton[][] botoes = new JButton[3][3];
    private JButton botaoReiniciar;
    private Tabuleiro tabuleiroObjeto;

    public JogoDosOito() {
        super("Jogo dos Oito");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));
        tabuleiroObjeto = new Tabuleiro( botoes);

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
                tabuleiroObjeto.reiniciarJogo();
            }
        });
        add(new JLabel(""));
        add(botaoReiniciar);
        add(new JLabel(""));

        addKeyListener(this);
        setFocusable(true);

        tabuleiroObjeto.atualizarTabuleiro();
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                mover(1, 0);
                break;
            case KeyEvent.VK_DOWN:
                mover(-1, 0);
                break;
            case KeyEvent.VK_LEFT:
                mover(0, 1);
                break;
            case KeyEvent.VK_RIGHT:
                mover(0, -1);
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    private void mover(int linha, int coluna) {
        tabuleiroObjeto.moverPeca(linha, coluna);
        if (tabuleiroObjeto.jogoConcluido()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            tabuleiroObjeto.reiniciarJogo();
        }
    }

   /* Pertencente ao código original (não mexido)
    private boolean movimentarPeca(int linha, int coluna) {
        if (linha > 0 && tabuleiro[linha - 1][coluna] == 0) {
            tabuleiro[linha - 1][coluna] = tabuleiro[linha][coluna];
            tabuleiro[linha][coluna] = 0;
            return true;
        } else if (linha < 2 && tabuleiro[linha + 1][coluna] == 0) {
            tabuleiro[linha + 1][coluna] = tabuleiro[linha][coluna];
            tabuleiro[linha][coluna] = 0;
            return true;
        } else if (coluna > 0 && tabuleiro[linha][coluna - 1] == 0) {
            tabuleiro[linha][coluna - 1] = tabuleiro[linha][coluna];
            tabuleiro[linha][coluna] = 0;
            return true;
        } else if (coluna < 2 && tabuleiro[linha][coluna + 1] == 0) {
            tabuleiro[linha][coluna + 1] = tabuleiro[linha][coluna];
            tabuleiro[linha][coluna] = 0;
            return true;
        }
        return false;
    }
    */
}