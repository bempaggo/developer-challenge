package chat.gpt.modelos;

public interface Regra {

    boolean fazerMovimento(Tabuleiro tabuleiro, Posicao poscisaoClicada);
    boolean verificarVitoria(Tabuleiro tabuleiro);
    void embaralharPecas(Tabuleiro tabuleiro);

}
