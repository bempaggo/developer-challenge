package main.java.chat.gpt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    private Board board;
    private List<Piece> pieceList;

    @BeforeEach
    public void setup() {
        pieceList = new ArrayList<>();
        board = new Board(pieceList);
    }

    @Test
    public void testGetPieceUsingPosition() {
        // Criar cenário de teste para a função do get por posição
        Piece piece1 = new Piece(1, 10);
        Piece piece2 = new Piece(2, 20);
        pieceList.add(piece1);
        pieceList.add(piece2);

        try {
            Piece result = board.getPieceUsingPosition(10);
            Assertions.assertEquals(piece1, result);
        } catch (Exception e) {
            Assertions.fail("Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    public void testGetPieceUsingValue() {
        // Criar cenário de teste para a função do get por valor
        Piece piece1 = new Piece(1, 10);
        Piece piece2 = new Piece(2, 20);
        pieceList.add(piece1);
        pieceList.add(piece2);

        try {
            Piece result = board.getPieceUsingValue(2);
            Assertions.assertEquals(piece2, result);
        } catch (Exception e) {
            Assertions.fail("Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    public void testGetEmptyPiece() {
        // Criar cenário de teste para a função do empty piece
        Piece emptyPiece = new Piece(9, 9);
        pieceList.add(emptyPiece);
        try {
            Piece result = board.getEmptyPiece();
            Assertions.assertEquals(emptyPiece, result);
        } catch (Exception e) {
            Assertions.fail("Exceção lançada: " + e.getMessage());
        }
    }
}
