package chat.gpt;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Tabuleiro extends JFrame {

    private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private Botao[][] botoes = new Botao[3][3];
	private Botao botaoReiniciar;

    public Tabuleiro() {
        super("Jogo dos Oito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		criarTabuleiro();

		setFocusable(true);
		setVisible(true);
    }

    private void criarTabuleiro() {
        // Cria as 9 peças de 0 até 8
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
                int valor = tabuleiro[i][j];
				Botao botao = new Botao(String.valueOf(valor));
				botoes[i][j] = botao;
				add(botao);
			}
		}
        
        // Cria o botão de reiniciar com a fonte padrão
		botaoReiniciar = new Botao("Reiniciar");
        botaoReiniciar.setFont(getFont());
		botaoReiniciar.addActionListener(e -> reiniciarJogo());
		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));
	}

    protected void mover(int linha, int coluna) {
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

		if (checarMovimentoInvalido(novaLinha, novaColuna))
			return;

        atualizarTabuleiro(linhaVazia, colunaVazia, novaLinha, novaColuna);

		atualizarVisaoTabuleiro();
		
		checarVitoria();
	}

    private boolean checarMovimentoInvalido(int novaLinha, int novaColuna) {
        return (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2);
    }

    private void checarVitoria() {
        if (jogoConcluido()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			reiniciarJogo();
		}
    }

    private boolean jogoConcluido() {
		int count = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] != count % 9)
					return false;
				count++;
			}
		}
		return true;
	}

    //Atualiza a posição de dois botões ( internamente em mover() )
    private void atualizarTabuleiro(int linhaAntiga, int colunaAntiga, int novaLinha, int novaColuna) {
        tabuleiro[linhaAntiga][colunaAntiga] = tabuleiro[novaLinha][novaColuna];
		tabuleiro[novaLinha][novaColuna] = 0;
    }

    //Atualiza a posição de todos os botões na tela (visão do usuário)
    private void atualizarVisaoTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Botao botao = botoes[i][j];
				int valor = tabuleiro[i][j];
				botao.setValor(String.valueOf(valor));
			}
		}
	}

    private void reiniciarJogo() {
		tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		atualizarVisaoTabuleiro();
	}
    
}
