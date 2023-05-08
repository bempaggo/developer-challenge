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
import javax.swing.JPanel;

public class JogoDosOitoGUI implements KeyListener {

	private JogoDosOito jogo;
	private JFrame janela;
	private JButton[][] botoes = new JButton[3][3];
	private JButton botaoReiniciar;

	public JogoDosOitoGUI(JogoDosOito jogo) {
		this.jogo = jogo;
	}

	public void criarJanela() {
		janela = new JFrame("Jogo dos Oito");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(300, 300);
		janela.setLayout(new GridLayout(4, 3));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botoes[i][j] = new JButton(Integer.toString(jogo.getValor(i, j)));
				botoes[i][j].setFont(new Font("Arial", Font.BOLD, 36));
				botoes[i][j].addActionListener(new BotaoListener(i, j));
				janela.add(botoes[i][j]);
			}
		}
		
		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo = new JogoDosOito();
				atualizarTabuleiro();
			}
		});

		
		janela.add(new JLabel(""));
		janela.add(botaoReiniciar);
		janela.add(new JLabel(""));
		
		janela.addKeyListener(this);;
		janela.setFocusable(true);
		atualizarTabuleiro();
		janela.setVisible(true);
	}

	private void atualizarTabuleiro() {
		jogo.atualizarTabuleiro(botoes);
	}

	private class BotaoListener implements ActionListener {

		private int linha;
		private int coluna;

		public BotaoListener(int linha, int coluna) {
			this.linha = linha;
			this.coluna = coluna;
		}

		public void actionPerformed(ActionEvent e) {
			jogo.mover(linha - 1, coluna - 1);
			atualizarTabuleiro();
			if (jogo.jogoConcluido()) {
				janela.setTitle("Parabéns, você ganhou!");
				jogo.reiniciarJogo(botoes);
			}
		}
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
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}