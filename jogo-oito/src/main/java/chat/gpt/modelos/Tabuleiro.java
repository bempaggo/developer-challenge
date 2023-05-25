package chat.gpt.modelos;

import java.util.HashMap;
import java.util.Map;

public class Tabuleiro {

    private final Integer DIMENSAO = 3;

    private final Map<Posicao, Peca> tabuleiro = new HashMap<>();

    public Tabuleiro() {
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        int count = 1;
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                this.tabuleiro.put(new Posicao(i, j), new Peca(count));
                count++;
            }
        }
    }

    public Peca getPeca(Posicao posicao) {
        return this.tabuleiro.get(posicao);
    }


    public Integer getDIMENSAO() {
        return DIMENSAO;
    }

    public Map<Posicao, Peca> getTabuleiro() {
        return tabuleiro;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res.append(this.tabuleiro.get(new Posicao(i, j)).getValor());
            }
            res.append("\n");
        }
        return res.toString();
    }
}
