package chat.gpt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tecla {
	
	private int valor;
	private int posicaoX;
	private int posicaoY;
	
    public boolean ehVazia() {
        return valor == 0;
    }

    public void movimentar(int linha, int coluna) {
        this.posicaoX += linha;
        this.posicaoY += coluna;
    }
}

