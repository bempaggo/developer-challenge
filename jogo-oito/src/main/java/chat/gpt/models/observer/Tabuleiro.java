package chat.gpt.models.observer;

import chat.gpt.models.Botao;

public class Tabuleiro {

    private int[][] tabuleiro = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	private Botao[][] botoes = new Botao[3][3];
	private Botao botaoReiniciar;

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
			TabuleiroGUI.mensagemVitoria();
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
    protected void atualizarVisaoTabuleiro() {
		TabuleiroGUI.atualizarVisaoTabuleiroGUI(this);
	}

    protected void reiniciarJogo() {
		tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		atualizarVisaoTabuleiro();
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public Botao[][] getBotoes() {
		return botoes;
	}

	public Botao getBotaoReiniciar() {
		return botaoReiniciar;
	}

	public void setBotaoReiniciar(Botao botaoReiniciar) {
		this.botaoReiniciar = botaoReiniciar;
	}
    
}
