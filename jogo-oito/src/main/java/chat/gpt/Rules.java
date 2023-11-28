package main.java.chat.gpt;

public class Rules {
    public Boolean winner(Board board) {
        return board.pieceList().stream().allMatch(Piece::rightPosition);
    }

    public boolean isValid(Piece movedPiece, Piece emptyPiece) {
        Boolean valid = false;
        if((movedPiece.getPosition() != emptyPiece.getPosition()) &&
                ((movedPiece.getPosition()+3 == emptyPiece.getPosition()) ||
                        (movedPiece.getPosition()-3 == emptyPiece.getPosition()) ||
                        (movedPiece.getPosition()+1 == emptyPiece.getPosition()) ||
                        (movedPiece.getPosition()-1 == emptyPiece.getPosition()))){
            valid = true;
        }
        return valid;
    }
}
