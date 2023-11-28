package chat.gpt.gui;

import javax.swing.*;

import chat.gpt.model.Tabuleiro;
import chat.gpt.model.Tecla;

import java.awt.*;
import java.awt.event.*;

public class JogoDosOito extends JFrame {
	private static final long serialVersionUID = 1L;
	private Tabuleiro tabuleiro;
	private JButton[][] botoes;

	public JogoDosOito() {
		super("Jogo dos Oito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		tabuleiro = new Tabuleiro();
		botoes = new JButton[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = criarBotao(i, j);
				botoes[i][j] = botao;
				add(botao);
			}
		}

		add(new JLabel(""));
		JButton botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(e -> {
			tabuleiro.reiniciarJogo();
			atualizarTabuleiro();
		});
		add(botaoReiniciar);
		add(new JLabel(""));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = botoes[i][j];
				botao.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						int keyCode = e.getKeyCode();
						moverTeclaPorTeclado(keyCode);
					}
				});

				botao.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						moverTeclaPorMouse(botao);
					}
				});
			}
		}

		atualizarTabuleiro();
		setVisible(true);
	}

	private JButton criarBotao(int linha, int coluna) {
		JButton botao = new JButton();
		botao.setFont(new Font("Arial", Font.BOLD, 36));
		return botao;
	}

	private void atualizarTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = botoes[i][j];
				Tecla tecla = tabuleiro.getTecla(i, j);
				if (tecla.ehVazia()) {
					botao.setText("");
				} else {
					botao.setText(String.valueOf(tecla.getValor()));
				}
			}
		}
	}

	private void moverTeclaPorMouse(JButton botao) {
		int linhaVazia = tabuleiro.getLinhaVazia();
		int colunaVazia = tabuleiro.getColunaVazia();
		int linhaBotao = -1;
		int colunaBotao = -1;

		// Encontra a posição do botão clicado no tabuleiro
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (botoes[i][j] == botao) {
					linhaBotao = i;
					colunaBotao = j;
					break;
				}
			}
		}

		// Calcula a diferença entre as posições do botão e da tecla vazia
		int diferencaLinha = linhaBotao - linhaVazia;
		int diferencaColuna = colunaBotao - colunaVazia;

		// Verifica se o movimento é válido e move a tecla no tabuleiro
		if ((Math.abs(diferencaLinha) == 1 && diferencaColuna == 0)
				|| (Math.abs(diferencaColuna) == 1 && diferencaLinha == 0)) {
			tabuleiro.movimentarTecla(diferencaLinha, diferencaColuna);
			atualizarTabuleiro();

			// Verifica se o jogo foi concluído
			if (tabuleiro.jogoConcluido()) {
				JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
				tabuleiro.reiniciarJogo();
				atualizarTabuleiro();
			}
		}
	}

	private void moverTeclaPorTeclado(int direcao) {
		int linha = 0;
		int coluna = 0;

		switch (direcao) {
		case KeyEvent.VK_UP:
			linha = -1;
			break;
		case KeyEvent.VK_DOWN:
			linha = 1;
			break;
		case KeyEvent.VK_LEFT:
			coluna = -1;
			break;
		case KeyEvent.VK_RIGHT:
			coluna = 1;
			break;
		}

		tabuleiro.movimentarTecla(linha, coluna);
		atualizarTabuleiro();
		if (tabuleiro.jogoConcluido()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			tabuleiro.reiniciarJogo();
			atualizarTabuleiro();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(JogoDosOito::new);
	}
}
