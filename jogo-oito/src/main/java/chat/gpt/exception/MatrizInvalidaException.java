package chat.gpt.exception;

public class MatrizInvalidaException extends RuntimeException {
    public MatrizInvalidaException() {
        super("A matriz de dificuldade é inválida");
    }
}
