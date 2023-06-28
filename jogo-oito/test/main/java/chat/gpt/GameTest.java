package main.java.chat.gpt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameTest {

    private Game game;
    private List<Button> buttonList;
    private Board board;
    private Rules rules;

    @BeforeEach
    public void setup() {
        rules = new Rules();
        game = new Game(rules);
        buttonList = game.getButtonList();
        board = game.getBoard();
    }

    @Test
    public void testShufflePieces() {
        game.shufflePieces();
        List<Piece> pieceList = game.board.pieceList();
        boolean isShuffled = false;
        for (int i = 0; i < pieceList.size(); i++) {
            if (pieceList.get(i).getPosition() != i) {
                isShuffled = true;
                break;
            }
        }
        Assertions.assertTrue(isShuffled, "As peças não foram embaralhadas corretamente.");
    }

    @Test
    public void testMakeMove(){
        try {
            boolean result = game.makeMove(8);
            Assertions.assertTrue(result, "A movimentação não foi considerada válida.");
        } catch (Exception e) {
            Assertions.fail("Exceção lançada: " + e.getMessage());
        }
    }

    @Test
    public void testCreateButtons() {
        Assertions.assertEquals(9, buttonList.size(), "O número de botões criados não é o esperado.");
        for (int i = 0; i < 9; i++) {
            JButton jButton = buttonList.get(i).getjButton();
            Assertions.assertNotNull(jButton, "O botão não foi criado corretamente.");
            Assertions.assertEquals(Font.BOLD, jButton.getFont().getStyle(), "O estilo da fonte do botão não é o esperado.");
            Assertions.assertEquals(40, jButton.getFont().getSize(), "O tamanho da fonte do botão não é o esperado.");
        }
    }
    @Test
    public void testGetClickedButton() throws Exception {
        JButton jButton = new JButton();
        Button expectedButton = new Button(jButton, 2);
        buttonList.add(expectedButton);
        Button result = game.getCLickedButton(jButton);
        Assertions.assertEquals(expectedButton, result, "O botão retornado não é o esperado.");
    }

    @Test
    public void testChangePieces() {
        Piece piece1 = new Piece(3, 0);
        Piece piece2 = new Piece(9, 1);
        int initialPos1 = piece1.getPosition();
        int initialPos2 = piece2.getPosition();
        game.changePieces(piece1, piece2);
        Assertions.assertEquals(initialPos2, piece1.getPosition(), "A posição da primeira peça não foi trocada corretamente.");
        Assertions.assertEquals(initialPos1, piece2.getPosition(), "A posição da segunda peça não foi trocada corretamente.");
    }

}
