package chat.gpt.exception;

public class TeclaInvalidaException extends RuntimeException {
    public TeclaInvalidaException() {
        super("Tecla inválida, use as teclas direcionais");
    }
    
}