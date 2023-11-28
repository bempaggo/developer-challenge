package main.java.chat.gpt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PieceTest {

    private Piece piece;

    @BeforeEach
    public void setup() {
        piece = new Piece(10, 5);
    }

    @Test
    public void testGetValue() {
        Integer result = piece.getValue();
        Assertions.assertEquals(10, result, "O valor retornado não é igual ao valor original.");
    }

    @Test
    public void testGetPosition() {
        Integer result = piece.getPosition();

        Assertions.assertEquals(5, result, "A posição retornada não é igual à posição original.");
    }

    @Test
    public void testSetPosition() {
        piece.setPosition(3);

        Integer result = piece.getPosition();
        Assertions.assertEquals(3, result, "A posição não foi alterada corretamente.");
    }

    @Test
    public void testGetStringValue() {

        Piece emptyPiece = new Piece(9, 2);

        String result1 = piece.getStringValue();
        String result2 = emptyPiece.getStringValue();

        Assertions.assertEquals("10", result1, "O valor retornado como string não é o esperado.");
        Assertions.assertEquals(" ", result2, "O valor retornado como string não é o esperado para uma peça vazia.");
    }

    @Test
    public void testIsEmpty() {

        Piece nonEmptyPiece = new Piece(5, 4);
        Piece emptyPiece = new Piece(9, 6);

        boolean result1 = piece.isEmpty();
        boolean result2 = nonEmptyPiece.isEmpty();
        boolean result3 = emptyPiece.isEmpty();

        Assertions.assertFalse(result1, "A peça não deveria ser considerada vazia.");
        Assertions.assertFalse(result2, "A peça não deveria ser considerada vazia.");
        Assertions.assertTrue(result3, "A peça deveria ser considerada vazia.");
    }

    @Test
    public void testRightPosition() {
        Piece piece1 = new Piece(1, 0);
        Piece piece2 = new Piece(3, 2);
        Piece piece3 = new Piece(7, 7);

        boolean result1 = piece.rightPosition();
        boolean result2 = piece1.rightPosition();
        boolean result3 = piece2.rightPosition();
        boolean result4 = piece3.rightPosition();

        Assertions.assertFalse(result1, "A peça deveria estar na posição errada.");
        Assertions.assertTrue(result2, "A peça deveria estar na posição correta.");
        Assertions.assertTrue(result3, "A peça deveria estar na posição correta.");
        Assertions.assertFalse(result4, "A peça deveria estar na posição errada.");
    }

}
