package chat.gpt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JogoDosOito extends JFrame {

	private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

	private JButton botaoReiniciar;
	private Table table;
	private Listener listener;
	private GameListener gameListener;

	public JogoDosOito(Table table, Listener listener, GameListener gameListener ) {
		super("Jogo dos Oito");
		this.table = table;
		this.listener = listener;
		this.gameListener = gameListener;
	}

	// private void registre(ArrayList<TableCell> list) {
	// 	// for (int i = 0; i < 3; i++) {
	// 	// 	for (int j = 0; j < 3; j++) {
	// 	// 		// JButton botao = new JButton();
	// 	// 		botao.setFont(new Font("Arial", Font.BOLD, 36));
	// 	// 		// botoes[i][j] = botao;
	// 	// 		add(botao);
	// 	// 	}
	// 	// }

	// }

	public void start(){
		for (TableCell cell : table.getBotoes()) {
			cell.setFont(new Font("Arial", Font.BOLD, 36));
			add(cell);
		}

		// table.atualizarTabuleiro();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarJogo();
			}
		});
		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));

		setFocusable(true);
		requestFocus();
		addKeyListener(listener);

		setVisible(true);
		// gameListener.notify("jogoConcluido", "Parabéns, você venceu!");
		// JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
	}

	// public void test(){
	// 	ActionEventDelegate<String> x =  (y) -> {
	// 		JOptionPane.showMessageDialog(this, y);
	// 	};
	// }

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
		// atualizarTabuleiro();
		// if (jogoConcluido()) {
		// 	JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
		// 	reiniciarJogo();
		// }
	}


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

	private void reiniciarJogo() {
		table.suffleTable();
	}
}
