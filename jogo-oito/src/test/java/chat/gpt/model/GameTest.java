package chat.gpt.model;

import chat.gpt.exception.ImpossibleMoveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chat.gpt.util.Constants.GAME_FINISHED;
import static chat.gpt.util.Constants.GRID_LENGTH;
import static chat.gpt.util.Constants.GRID_WIDTH;
import static chat.gpt.util.Constants.MOVE_DOWN;
import static chat.gpt.util.Constants.MOVE_LEFT;
import static chat.gpt.util.Constants.MOVE_RIGHT;
import static chat.gpt.util.Constants.MOVE_UP;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        Grid grid = new Grid();
        game = new Game(grid);
    }

    @Test
    public void testConstructor() {
        int[][] grid = game.gridActualState();
        int expectedGridLength = grid.length;
        int expectedGridWidth = grid[0].length;

        Assertions.assertEquals(GRID_WIDTH, expectedGridWidth);
        Assertions.assertEquals(GRID_LENGTH, expectedGridLength);

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
        int[][] finalGrid = game.gridActualState();

        for (int i = 0; i < GAME_FINISHED.length; i++) {
            System.arraycopy(GAME_FINISHED[i], 0, finalGrid[i], 0, GAME_FINISHED[i].length);
        }

        Assertions.assertTrue(game.gameIsComplete());
    }

    @Test
    public void testResetGame() {
        int[][] gridBefore = game.gridActualState();
        game.resetGame();
        int[][] gridAfter = game.gridActualState();
        assertNotSame(gridBefore, gridAfter);
    }

    @Test
    public void testGridActualState() {
        int[][] grid = game.gridActualState();
        grid[0][0] = 100;
        int[][] newGrid = game.gridActualState();
        assertEquals(1, newGrid[0][0]);
    }

    @Test
    public void testMoveUp() {
        int[][] initialGrid = {
                { 1, 2, 3 },
                { 0, 5, 6 },
                { 4, 7, 8 }
        };
        game.loadGrid(initialGrid);
        game.move(MOVE_UP);
        int[][] expectedGrid = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 0, 7, 8 }
        };
        assertArrayEquals(expectedGrid, game.gridActualState());
    }

    @Test
    public void testMoveDown() {
        int[][] initialGrid = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 0, 7, 8 }
        };
        game.loadGrid(initialGrid);
        game.move(MOVE_DOWN);
        int[][] expectedGrid = {
                { 1, 2, 3 },
                { 0, 5, 6 },
                { 4, 7, 8 }
        };
        assertArrayEquals(expectedGrid, game.gridActualState());
    }

    @Test
    public void testMoveLeft() {
        int[][] initialGrid = {
                { 1, 2, 3 },
                { 4, 0, 6 },
                { 7, 8, 5 }
        };
        game.loadGrid(initialGrid);
        game.move(MOVE_LEFT);
        int[][] expectedGrid = {
                { 1, 2, 3 },
                { 4, 6, 0 },
                { 7, 8, 5 }
        };
        assertArrayEquals(expectedGrid, game.gridActualState());
    }

    @Test
    public void testMoveRight() {
        int[][] initialGrid = {
                { 1, 2, 3 },
                { 4, 5, 0 },
                { 7, 8, 6 }
        };
        game.loadGrid(initialGrid);
        game.move(MOVE_RIGHT);
        int[][] expectedGrid = {
                { 1, 2, 3 },
                { 4, 0, 5 },
                { 7, 8, 6 }
        };
        assertArrayEquals(expectedGrid, game.gridActualState());
    }

    @Test
    public void testImpossibleMove() {
        int[][] gridMovesDownRightAllowed = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 0 }
        };
        int[][] gridMovesUpLeftAllowed = {
                { 0, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 1 }
        };

        game.loadGrid(gridMovesDownRightAllowed);
        assertThrows(ImpossibleMoveException.class, () -> game.move(MOVE_UP));
        assertThrows(ImpossibleMoveException.class, () -> game.move(MOVE_LEFT));

        game.loadGrid(gridMovesUpLeftAllowed);
        assertThrows(ImpossibleMoveException.class, () -> game.move(MOVE_DOWN));
        assertThrows(ImpossibleMoveException.class, () -> game.move(MOVE_RIGHT));
    }

}