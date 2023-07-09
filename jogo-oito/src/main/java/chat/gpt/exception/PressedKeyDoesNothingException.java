package chat.gpt.exception;

public class PressedKeyDoesNothingException extends RuntimeException {
    
    public PressedKeyDoesNothingException() {
        super("Tecla inválida, use as teclas direcionais");
    }
    
}