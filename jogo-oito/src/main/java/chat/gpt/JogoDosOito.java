package chat.gpt;

import javax.swing.JButton;

public class JogoDosOito {

    private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    public void mover(int linha, int coluna) {
        int lAtual = linha;
        int cAtual = coluna;

        // Certifica-se que só moverá se não for botão vazio
        if (tabuleiro[lAtual][cAtual] != 0) {

            // verifica se pode subir
            if (lAtual > 0 && tabuleiro[lAtual - 1][cAtual] == 0) {
                // pega valor do botão
                int valorBotao = tabuleiro[lAtual][cAtual];
                // move o valor atual para cima
                tabuleiro[lAtual - 1][cAtual] = valorBotao;
                tabuleiro[lAtual][cAtual] = 0;

            }
            // Verifica se pode ir para a esquerda
            else if (cAtual > 0 && tabuleiro[lAtual][cAtual - 1] == 0) {
                // pega valor do botão
                int valorBotao = tabuleiro[lAtual][cAtual];
                // move o valor atual para esquerda
                tabuleiro[lAtual][cAtual - 1] = valorBotao;
                tabuleiro[lAtual][cAtual] = 0;
            }

            // Verifica se pode ir para a direita
            else if (cAtual < 2 && tabuleiro[lAtual][cAtual + 1] == 0) {
                // pega valor do botão
                int valorBotao = tabuleiro[lAtual][cAtual];
                // move o valor atual para direita
                tabuleiro[lAtual][cAtual + 1] = valorBotao;
                tabuleiro[lAtual][cAtual] = 0;
            }

            // Verifica se pode descer
            else if (lAtual < 2 && tabuleiro[lAtual + 1][cAtual] == 0) {
                // pega valor do botão
                int valorBotao = tabuleiro[lAtual][cAtual];
                // move o valor atual para baixo
                tabuleiro[lAtual + 1][cAtual] = valorBotao;
                tabuleiro[lAtual][cAtual] = 0;
            }
        }
    }
    
    public boolean movimentarPeca(int linha, int coluna) {
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