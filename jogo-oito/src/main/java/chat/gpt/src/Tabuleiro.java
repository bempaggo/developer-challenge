package chat.gpt.modelos;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tabuleiro {

    private final Integer DIMENSAO = 3;
    private final Integer VALOR_PECA_VAZIA = DIMENSAO * DIMENSAO;

    private final Map<Posicao, Peca> tabuleiro = new HashMap<>();

    public Tabuleiro() {
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        int count = 1;

        for (int i = 0; i < this.DIMENSAO; i++) {
            for (int j = 0; j < this.DIMENSAO; j++) {
                this.tabuleiro.put(new Posicao(i, j), new Peca(count));
                count++;
            }
        }
    }

    public void reiniciarTabuleiro() {
        this.tabuleiro.clear();
        inicializarTabuleiro();
    }

    public void embaralharPecas() {
        this.tabuleiro.clear();

        List<Integer> numeros = IntStream.rangeClosed(1, this.DIMENSAO * this.DIMENSAO)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numeros);

        int index = 0;

        for (int i = 0; i < this.DIMENSAO; i++) {
            for (int j = 0; j < this.DIMENSAO; j++) {
                this.tabuleiro.put(new Posicao(i, j), new Peca(numeros.get(index)));
                index++;
            }
        }

    }

    public Peca getPeca(Posicao posicao) {
        return this.tabuleiro.get(posicao);
    }


    public Integer getDimensao() {
        return this.DIMENSAO;
    }

    public Integer getValorPecaVazia() {
        return this.VALOR_PECA_VAZIA;
    }

    public Map<Posicao, Peca> getTabuleiro() {
        return tabuleiro;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.DIMENSAO; i++) {
            for (int j = 0; j < this.DIMENSAO; j++) {
                res.append(this.tabuleiro.get(new Posicao(i, j)).getValor());
            }
            res.append("\n");
        }
        return res.toString();
    }
}
