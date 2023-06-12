package chat.gpt;

import java.util.HashMap;
import java.util.Map;

public class Board{

    private final Map<Position, Piece> boardMap = new HashMap<>();
    
    //construtor inicial com instruções para começar o tabuleiro
    public Board(){
        startBoard();
    }

    public void startBoard(){
        Integer pieceCounter = 0;
        for (Integer i = 0; i < 3; i++) {
            for (Integer j = 0; j < 3; j++) {
                this.boardMap.put(new Position(i, j), new Piece(pieceCounter));
                pieceCounter++;
            }
        }
    }

    public void restartBoard() {
        this.boardMap.clear();
        startBoard();
    }


}