package chat.gpt.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException() {
        super("Não foi encontrada a posição no tabuleiro");
    }
}