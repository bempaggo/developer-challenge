package chat.gpt.model;

import lombok.Getter;

@Getter
public class Tabuleiro {
	private Tecla[][] teclas;
	private int linhaVazia;
	private int colunaVazia;

	public Tabuleiro() {
		teclas = new Tecla[3][3];
		linhaVazia = 2;
		colunaVazia = 2;
		inicializarTeclas();
	}

	private void inicializarTeclas() {
		int valor = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				teclas[i][j] = new Tecla(valor, i, j);
				valor++;
			}
		}
		teclas[2][2].setValor(0);
	}

	public Tecla getTecla(int linha, int coluna) {
		return teclas[linha][coluna];
	}

	public void movimentarTecla(int linha, int coluna) {
		int novaLinha = linhaVazia + linha;
		int novaColuna = colunaVazia + coluna;

		if (novaLinha >= 0 && novaLinha < 3 && novaColuna >= 0 && novaColuna < 3) {
			Tecla teclaVazia = teclas[linhaVazia][colunaVazia];
			Tecla teclaMovida = teclas[novaLinha][novaColuna];
			teclaVazia.movimentar(linha, coluna);
			teclaMovida.movimentar(-linha, -coluna);
			teclas[linhaVazia][colunaVazia] = teclaMovida;
			teclas[novaLinha][novaColuna] = teclaVazia;
			linhaVazia = novaLinha;
			colunaVazia = novaColuna;
		}
	}

	public boolean jogoConcluido() {
		int valor = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Tecla tecla = teclas[i][j];
				if (i == 2 && j == 2) {
					if (tecla.getValor() != 0) {
						return false;
					}
				} else {
					if (tecla.getValor() != valor) {
						return false;
					}
					valor++;
				}
			}
		}
		return true;
	}

	public void reiniciarJogo() {
		inicializarTeclas();
		linhaVazia = 2;
		colunaVazia = 2;
	}
}
