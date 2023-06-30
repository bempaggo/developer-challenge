package chat.gpt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chat.gpt.util.Constants;

public class GameTest {

    private Game Game;

    @BeforeEach
    public void setUp() {
        Game = new Game();
    }

    @Test
    public void testConstructor() {
        Game game = new Game();


        int[][] grid = game.gridActualState();
        int expectedGridLength = grid.length;
        int expectedGridWidth = grid[0].length;

    
        Assertions.assertEquals(Constants.GRID_WIDTH, expectedGridWidth);
        Assertions.assertEquals(Constants.GRID_LENGTH, expectedGridWidth);

        int expectedValue = 1;
        for (int i = 0; i < expectedGridLength; i++) {
            for (int j = 0; j < expectedGridWidth; j++) {
                if (i == expectedGridLength - 1 && j == expectedGridWidth - 1) {
                    Assertions.assertEquals(0, grid[i][j]);
                } else {
                    Assertions.assertEquals(expectedValue, grid[i][j]);
                    expectedValue++;
                }
            }
        }
    }

    @Test
    public void testGameIsComplete() {
        int[][] finalGrid = Game.gridActualState();

        int[][] GAME_FINISHED = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };
        for (int i = 0; i < GAME_FINISHED.length; i++) {
            System.arraycopy(GAME_FINISHED[i], 0, finalGrid[i], 0, GAME_FINISHED[i].length);
        }

        Assertions.assertTrue(Game.gameIsComplete());
    }
   

}
