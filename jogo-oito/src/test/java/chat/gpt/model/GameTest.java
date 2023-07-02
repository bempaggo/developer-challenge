package chat.gpt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chat.gpt.util.Constants;

public class GameTest {

    private Game Game;

    @BeforeEach
    public void setUp() {
        Game = chat.gpt.model.Game.getInstance();
    }

    @Test
    public void testConstructor() {
        Game game = chat.gpt.model.Game.getInstance();
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

    @Test
    public void testRestartGame() {
        Game game = chat.gpt.model.Game.getInstance();

        // Obter a referência do objeto Grid antes de reiniciar o jogo
        int[][] gridAntes = game.gridActualState();

        // Reiniciar o jogo
        game.resetGame();

        // Obter a referência do objeto Grid após reiniciar o jogo
        int[][] gridDepois = game.gridActualState();

        // Verificar se as referências são diferentes
        assertNotSame(gridAntes, gridDepois);

    }

    @Test
    public void testGridActualState() {
        Game game = chat.gpt.model.Game.getInstance();
        int[][] grid = game.gridActualState();

        grid[0][0] = 100;

        int[][] gridNovo = game.gridActualState();

        assertEquals(1, gridNovo[0][0]);
    }

}
