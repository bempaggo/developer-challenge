package chat.gpt.src;

import chat.gpt.src.exception.JogoException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tabuleiro {

    private final Integer dimensao;
    private final Map<Posicao, Peca> tabuleiro = new HashMap<>();

    public Tabuleiro(Integer dimensao) {
        if (dimensao < 3) {
            throw new JogoException("Dimensão deve ser pelo menos 3");
        }
        this.dimensao = dimensao;
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        int count = 1;

        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
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

        List<Integer> numeros = IntStream.rangeClosed(1, this.dimensao * this.dimensao)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numeros);

        int index = 0;

        for (int i = 0; i < this.dimensao; i++) {
            for (int j = 0; j < this.dimensao; j++) {
                this.tabuleiro.put(new Posicao(i, j), new Peca(numeros.get(index)));
                index++;
            }
        }

    }

    public Peca getPeca(Posicao posicao) {
        return this.tabuleiro.get(posicao);
    }


    public Integer getDimensao() {
        return this.dimensao;
    }

    public Integer getValorPecaVazia() {
        return this.dimensao * this.dimensao;
    }

    public Map<Posicao, Peca> getTabuleiro() {
        return tabuleiro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tabuleiro tabuleiro1 = (Tabuleiro) o;

        if (!dimensao.equals(tabuleiro1.dimensao)) return false;
        return tabuleiro.equals(tabuleiro1.tabuleiro);
    }

    @Override
    public int hashCode() {
        int result = dimensao.hashCode();
        result = 31 * result + tabuleiro.hashCode();
        return result;
    }
}
