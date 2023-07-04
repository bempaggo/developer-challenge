package chat.gpt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }   

    @Test
    void gameIsComplete_GameNotComplete_ReturnsFalse() {
        // Arrange & Act
        boolean isComplete = game.gameIsComplete();

        // Assert
        assertFalse(isComplete);
    }
}
