package controller;

import model.BoardInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class GameControllerTest {

    private GameController gameController;

    @Mock
    private BoardInterface board;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameController = new GameController();
        gameController.setBoard(board);
    }

    @Test
    void testNotifyMoveGameNotComplete() {
        List<Integer> boardData = generateRandomList();
        List<Integer> gameCompletePattern = generateRandomList();

        when(board.getBoardData()).thenReturn(boardData);
        when(board.getGameIsCompleteBoardPattern()).thenReturn(gameCompletePattern);

    // Verificar se o boardCompletePattern é diferente do boardData
    assertNotEquals(gameCompletePattern, boardData);

    }

    @Test
    void testNotifyMoveGameComplete() {
        List<Integer> boardData = generateRandomList();

        when(board.getBoardData()).thenReturn(boardData);
        when(board.getGameIsCompleteBoardPattern()).thenReturn(new ArrayList<>(boardData));

        
    }

    private List<Integer> generateRandomList() {
        List<Integer> list = new ArrayList<>();
        list.add(ThreadLocalRandom.current().nextInt()); // Adiciona um valor aleatório à lista
        return list;
    }

    @Test
    void testResetGame() {
        gameController.resetGame();

        verify(board).reset();
    }
}
