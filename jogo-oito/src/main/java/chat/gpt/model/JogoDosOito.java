
package chat.gpt.model;

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

	private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private JButton[][] botoes = new JButton[3][3];
	private JButton botaoReiniciar;

	public JogoDosOito() {
		super("Jogo dos Oito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

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
		int linhaVazia = -1;
		int colunaVazia = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == 0) {
					linhaVazia = i;
					colunaVazia = j;
				}
			}
		}
		int novaLinha = linhaVazia + linha;
		int novaColuna = colunaVazia + coluna;
		if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
			// movimento inválido
			return;
		}
		tabuleiro[linhaVazia][colunaVazia] = tabuleiro[novaLinha][novaColuna];
		tabuleiro[novaLinha][novaColuna] = 0;
		atualizarTabuleiro();
		if (jogoConcluido()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			reiniciarJogo();
		}
	}

	private boolean jogoConcluido() {
		int count = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] != count % 9) {
					return false;
				}
				count++;
			}
		}
		return true;
	}

	private void atualizarTabuleiro() {
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

	private void reiniciarJogo() {
		tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		atualizarTabuleiro();
	}
}
