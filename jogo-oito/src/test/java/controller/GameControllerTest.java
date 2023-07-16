package controller;

import model.BoardInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.GameUI;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;

    @Mock
    private BoardInterface grid;

    @Mock
    private GameUI view;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController();
        gameController.setBoard(grid);
        gameController.setView(view);
    }

    @Test
    void testNotifyMoveGameNotComplete() {
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));
        when(grid.getGameIsCompleteBoardPattern()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));

        gameController.notifyMove();

        verify(view).updateBoard();
    }

    @Test
    void testNotifyMoveGameComplete() {
        when(grid.getBoardData()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));
        when(grid.getGameIsCompleteBoardPattern()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));

        gameController.notifyMove();

        verify(view).updateBoard();
    }

    @Test
    void testResetGame() {
        gameController.resetGame();

        verify(grid).reset();
        verify(view).updateBoard();
    }
}

