package chat.gpt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JogoDosOito extends JFrame implements KeyListener {

    private Tabuleiro tabuleiro = new Tabuleiro();
    private JButton[][] botoes = new JButton[3][3];
    private List<Peca> pecas;
    private JButton botaoReiniciar;

    public JogoDosOito() {
        super("Jogo dos Oito");
        this.pecas = new ArrayList<Peca>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(4, 3));

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = new JButton();
                botao.setFont(new Font("Arial", Font.BOLD, 36));
                botoes[i][j] = botao;
                add(botao);
                if (count < 8) {
                    count++;
                    pecas.add(new Peca(tabuleiro.getValue(i, j), i, j));
                }
                botoes[i][j].addActionListener((ActionEvent e) -> {
                    pecas.forEach(c -> {
                        if (e.getActionCommand().equals(String.valueOf(c.getNumber()))){
                            mover(c.getNumX(), c.getNumY());
                        }
                    });
                });
            }
        }

        botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    reiniciarJogo();
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

    private void mover(int linha, int coluna) {
        int linhaVazia = -1;
        int colunaVazia = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.getValue(i, j) == 0) {
                        linhaVazia = i;
                        colunaVazia = j;
                }
            }
        }

        if ((linhaVazia != linha || colunaVazia != coluna)) {
            if (linhaVazia == linha) {
                if ((Math.abs(coluna - colunaVazia) < 2)) {
                    movimentarPeca(linha, coluna, linhaVazia, colunaVazia);
                }
            } else if (colunaVazia == coluna) {
                if (Math.abs(linha - linhaVazia) < 2) {
                    movimentarPeca(linha, coluna, linhaVazia, colunaVazia);
                }
            }
        }
    }

    public static void main(String[] args) {
        new JogoDosOito();
    }

    public boolean jogoConcluido() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                        if (tabuleiro.getValue(i, j) != count % 9) {
                                return false;
                        }
                        count++;
                }
        }
        return true;
    }

    private void movimentarPeca(int linha, int coluna, int linhaVazia, int colunaVazia) {
        tabuleiro.setValue(linhaVazia, colunaVazia, tabuleiro.getValue(linha, coluna));
        tabuleiro.setValue(linha, coluna, 0);
        atualizarTabuleiro();
        //this.tabuleiro.ordenar();
        if (jogoConcluido()) {
            JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
            reiniciarJogo();
        }
    }

    private void atualizarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton botao = botoes[i][j];
                int valor = tabuleiro.getValue(i, j);
                if (valor == 0) {
                    botao.setText("");
                } else {
                    botao.setText(String.valueOf(valor));
                }
                botoes[i][j] = botao;
                for (int e = 0; e < 8; e++) {
                    if (pecas.get(e).getNumber() == valor){
                        pecas.get(e).setNumX(i);
                        pecas.get(e).setNumY(j);
                        pecas.get(e).setNumber(valor);
                    }
                }
            }
        }
    }

    private void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
        atualizarTabuleiro();
    }

    public void ordenar() {
        this.tabuleiro.ordenar();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
