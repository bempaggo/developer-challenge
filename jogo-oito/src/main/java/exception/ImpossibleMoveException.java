package exception;

public class ImpossibleMoveException extends RuntimeException{

    public ImpossibleMoveException() {
        super("Movimento Inválido");
    }
     
}