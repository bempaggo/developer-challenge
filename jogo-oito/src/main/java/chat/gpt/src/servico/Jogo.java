package chat.gpt.src.servico;

import chat.gpt.src.modelo.Peca;
import chat.gpt.src.modelo.Tabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Jogo {

    private Tabuleiro tabuleiro;
    private final Regra regra;

    public Jogo() {
        regra = new RegraImpl();
        iniciar();
    }

    private void iniciar() {
        this.tabuleiro = new Tabuleiro(configuracaoInicial());
    }

    private List<Peca> configuracaoInicial() {
        List<Peca> pecasInicial = new ArrayList<>();
        IntStream.range(0, 9).forEach(i -> pecasInicial.add(new Peca(i + 1, i)));
        return pecasInicial;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void embaralharPecas() {
        List<Peca> pecas = this.tabuleiro.getPecas();
        List<Integer> posicoes = IntStream.range(0, 9).boxed().collect(Collectors.toList());

        Collections.shuffle(posicoes);
        IntStream.range(0, 9).forEach(i -> pecas.get(i).setPosicao(posicoes.get(i)));
    }

    public void reiniciar() {
        iniciar();
    }

    public void fazerMovimento(Integer valorMovido) {
        Peca pecaMovida = tabuleiro.getPecaPorValor(valorMovido);
        Peca pecaVazia = tabuleiro.getPecaVazia();
        Boolean movimentoValido = regra.validarMovimento(pecaMovida, pecaVazia);

        if (Boolean.TRUE.equals(movimentoValido)) {
            trocarPecas(pecaMovida, pecaVazia);
        }
    }

    private void trocarPecas(Peca pecaMovida, Peca pecaVazia) {
        Integer posAux = pecaMovida.getPosicao();
        pecaMovida.setPosicao(pecaVazia.getPosicao());
        pecaVazia.setPosicao(posAux);
    }

    public Boolean verificarVitoria() {
        return regra.verificarVitoria(tabuleiro);
    }
}
