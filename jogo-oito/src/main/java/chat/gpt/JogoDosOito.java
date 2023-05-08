package chat.gpt;

import javax.swing.JButton;

public class JogoDosOito {

    private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    public void mover(int linha, int coluna) {
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

    public boolean jogoConcluido() {
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

    public int getValor(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }
    
    public void atualizarTabuleiro(JButton[][] botoes) {
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

	public void reiniciarJogo(JButton[][] botoes) {
		tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		atualizarTabuleiro(botoes);
	}

    public static void main(String[] args) {
        JogoDosOito jogo = new JogoDosOito();
        JogoDosOitoGUI gui = new JogoDosOitoGUI(jogo);
        gui.criarJanela();
    }
}