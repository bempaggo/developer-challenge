package chat.gpt.exception;

public class PosicaoVaziaNaoEncontradaException extends RuntimeException {
    public PosicaoVaziaNaoEncontradaException() {
        super("Não foi encontrada uma posição vazia no tabuleiro ou o tabuleiro inicial é inválido.");
    }
}