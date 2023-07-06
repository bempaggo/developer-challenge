package chat.gpt.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chat.gpt.controller.GameService;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private GameService game;

    @BeforeEach
    void setUp() {
        game = new GameService();
    }   

    @Test
    void gameIsComplete_GameNotComplete_ReturnsFalse() {
        // Arrange & Act
        boolean isComplete = game.gameIsComplete();

        // Assert
        assertFalse(isComplete);
    }
}
