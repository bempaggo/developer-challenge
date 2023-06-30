package chat.gpt.exception;

public class GridDoesNotFeatStandardsException extends RuntimeException {
    public GridDoesNotFeatStandardsException() {
        super("A matriz de dificuldade é inválida");
    }
}
