package controller;

import model.BoardInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    }

    @Test
    void testNotifyMoveGameNotComplete() {
        List<Integer> boardData = generateRandomList();
        List<Integer> gameCompletePattern = generateRandomList();

        when(grid.getBoardData()).thenReturn(boardData);
        when(grid.getGameIsCompleteBoardPattern()).thenReturn(gameCompletePattern);

        verify(view).updateBoard();
    }

    @Test
    void testNotifyMoveGameComplete() {
        List<Integer> boardData = generateRandomList();

        when(grid.getBoardData()).thenReturn(boardData);
        when(grid.getGameIsCompleteBoardPattern()).thenReturn(new ArrayList<>(boardData));

        verify(view).updateBoard();
    }

    private List<Integer> generateRandomList() {
        List<Integer> list = new ArrayList<>();
        list.add(ThreadLocalRandom.current().nextInt()); // Adiciona um valor aleatório à lista
        return list;
    }

    @Test
    void testResetGame() {
        gameController.resetGame();

        verify(grid).reset();
        verify(view).updateBoard();
    }
}
