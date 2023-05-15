package chat.gpt;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JogoDosOito extends JFrame {

	private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private final JButton[][] botoes = new JButton[3][3];

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

		JButton botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(e -> reiniciarJogo());
		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));

		moverBotao();
		setFocusable(true);
		reiniciarJogo();
		setVisible(true);
	}

	private void mover(int linha, int coluna) {

			if (linha < 2 && tabuleiro[linha + 1][coluna] == 0) {
				tabuleiro[linha + 1][coluna] = tabuleiro[linha][coluna];
				tabuleiro[linha][coluna] = 0;
			}else if (linha > 0 && tabuleiro[linha - 1][coluna] == 0) {
				tabuleiro[linha - 1][coluna] = tabuleiro[linha][coluna];
				tabuleiro[linha][coluna] = 0;
			}else if (coluna > 0 && tabuleiro[linha][coluna - 1] == 0) {
				tabuleiro[linha][coluna - 1] = tabuleiro[linha][coluna];
				tabuleiro[linha][coluna] = 0;
			}else if (coluna < 2 && tabuleiro[linha][coluna + 1] == 0) {
				tabuleiro[linha][coluna + 1] = tabuleiro[linha][coluna];
				tabuleiro[linha][coluna] = 0;
			}

		atualizarTabuleiro(tabuleiro);
		if (jogoConcluido()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			reiniciarJogo();
		}
	}

	public static void main(String[] args) {
		new JogoDosOito();
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

	private void atualizarTabuleiro(int[][] tabuleiro) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int valor = tabuleiro[i][j];
				if (valor == 0) {
					botoes[i][j].setText("");
				} else {
					botoes[i][j].setText(String.valueOf(valor));
				}
			}
		}
	}

	private void reiniciarJogo() {

		Object[] casas = aleatorios().toArray();
		tabuleiro = new int[3][3];

        int cont = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++) {
				tabuleiro[i][j] = Integer.parseInt(casas[cont].toString());
				cont++;
			}
		}
		atualizarTabuleiro(tabuleiro);
	}

	private Set<Integer> aleatorios(){
		Set<Integer> teste = new LinkedHashSet<>();
		int cont = 0;
		while(cont >= 0) {
			teste.add(new Random().nextInt(9));
			cont++;
			if(teste.size() == 9){
				break;
			}
		}
		return teste;
	}

	private void moverBotao(){
		for(int i = 0 ; i < 3; i++){
			for(int j = 0; j < 3; j++){
				int finalI1 = i;
				int finalJ1 = j;
				botoes[i][j].addActionListener(e -> mover(finalI1, finalJ1));
			}
		}
	}
}
