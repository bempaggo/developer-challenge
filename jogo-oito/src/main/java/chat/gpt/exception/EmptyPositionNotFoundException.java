package chat.gpt.exception;

public class EmptyPositionNotFoundException extends RuntimeException {
    public EmptyPositionNotFoundException() {
        super("Não foi encontrada uma posição vazia no tabuleiro ou o tabuleiro inicial é inválido.");
    }
}