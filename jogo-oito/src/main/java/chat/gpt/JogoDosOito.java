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

	private Tabuleiro tabuleiro;
	private JButton[][] botoes;
	private JButton botaoReiniciar;

	public JogoDosOito() {
		super("Jogo dos Oito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		tabuleiro = new Tabuleiro();
		botoes = new JButton[3][3];

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

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			tabuleiro.mover(1, 0);
			break;
		case KeyEvent.VK_DOWN:
			tabuleiro.mover(-1, 0);
			break;
		case KeyEvent.VK_LEFT:
			tabuleiro.mover(0, 1);
			break;
		case KeyEvent.VK_RIGHT:
			tabuleiro.mover(0, -1);
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		new JogoDosOito();
	}

	private void atualizarTabuleiro() {
		int[][] estadoTabuleiro = tabuleiro.getEstado();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = botoes[i][j];
				int valor = estadoTabuleiro[i][j];
				if (valor == 0) {
					botao.setText("");
				} else {
					botao.setText(String.valueOf(valor));
				}
			}
		}
	}

	private void reiniciarJogo() {
		tabuleiro.reiniciar();
		atualizarTabuleiro();
	}
}