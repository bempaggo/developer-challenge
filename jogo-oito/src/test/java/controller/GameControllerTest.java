package controller;

import model.GridInterface;
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
    private GridInterface grid;

    @Mock
    private GameUI view;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController();
        gameController.setGrid(grid);
        gameController.setView(view);
    }

    @Test
    void testNotifyMoveGameNotComplete() {
        when(grid.getGridData()).thenReturn(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7, 8));
        when(grid.getGameIsCompleteGridPattern()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));

        gameController.notifyMove();

        verify(view).updateGrid();
    }

    @Test
    void testNotifyMoveGameComplete() {
        when(grid.getGridData()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));
        when(grid.getGameIsCompleteGridPattern()).thenReturn(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));

        gameController.notifyMove();

        verify(view).updateGrid();
    }

    @Test
    void testResetGame() {
        gameController.resetGame();

        verify(grid).reset();
        verify(view).updateGrid();
    }
}

