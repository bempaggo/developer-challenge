package chat.gpt.src.modelo;

import chat.gpt.src.exception.JogoException;

import java.util.List;

public record Tabuleiro(List<Peca> pecas) {

    public Peca getPecaPorPosicao(Integer posicao) {
        return this.pecas.stream()
                .filter(peca -> peca.getPosicao().equals(posicao))
                .findFirst()
                .orElseThrow(() -> new JogoException("Peca da posicao " + posicao + " nao foi encontrada!"));
    }

    public Peca getPecaPorValor(Integer valor) {
        return this.pecas.stream()
                .filter(peca -> peca.getValor().equals(valor))
                .findFirst()
                .orElseThrow(() -> new JogoException("Peca com valor " + valor + " nao foi encontrada!"));
    }

    public Peca getPecaVazia() {
        return this.pecas.stream()
                .filter(Peca::ehPecaVazia)
                .findFirst()
                .orElseThrow(() -> new JogoException("Peca vazia nao foi encontrada!"));
    }
}
