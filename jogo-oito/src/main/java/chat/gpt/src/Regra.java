package chat.gpt.src;

public interface Regra {

    boolean fazerMovimento(Tabuleiro tabuleiro, Posicao posicaoClicada);

    boolean verificarVitoria(Tabuleiro tabuleiro);

    void reiniciarJogo(Tabuleiro tabuleiro);

    void embaralharPecas(Tabuleiro tabuleiro);

}
