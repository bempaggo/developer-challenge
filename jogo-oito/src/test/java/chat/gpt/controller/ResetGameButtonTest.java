package chat.gpt.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResetGameButtonTest {

    private boolean gameWasReseted;

    @Test
    public void testResetGame() {
        ResetGameButtonListener listener = () -> gameWasReseted = true;
        listener.resetGame();

        Assertions.assertTrue(gameWasReseted);
    }
}
