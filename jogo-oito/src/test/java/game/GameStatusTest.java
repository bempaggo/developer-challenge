package game;

import interfaces.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;
import util.GameStatus;

class GameStatusTest {

    private GameStatus gameStatus;
    private Graph board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.feedback();
        gameStatus = GameStatus.of(board);
    }

    @Test
    void testIsOver() {
        Assertions.assertTrue(this.gameStatus.isOver());

        board.setting();
        Assertions.assertFalse(this.gameStatus.isOver());
    }

}