package util;

import factories.GameFactoryImpl;
import interfaces.Vertex;
import listeners.BoardUpdateListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new GameFactoryImpl());
        board.initializeBoard();
    }

    @RepeatedTest(5)
    void initializeBoardTest() {
        board.initializeBoard();

        assertNotNull(board.getCells());
        assertFalse(board.isGameComplete());
    }

    @Test
    public void testSetBoardAsSolved() {
        board.setBoardAsSolved();

        assertNotNull(board.getCells());
        assertTrue(board.isGameComplete());
    }


    @Test
    void shuffleCellsTest() {
        // Initialize board
        board.initializeBoard();

        // Deep copy cells before shuffle for asserting purposes
        List<Vertex> cells = board.getCells();
        List<Vertex> cellsCopy = new ArrayList<>();
        for (Vertex cell : cells) {
            cellsCopy.add(cell.clone());
        }

        // Assert cells are same
        assertEquals(cells, cellsCopy);

        // Calls shuffle
        board.shuffleCells();

        // Assert cells change
        assertNotEquals(cells, cellsCopy);
    }

    @RepeatedTest(5)
    void isGameCompleteTest() {
        //Set board to solved then checking its completion
        board.setBoardAsSolved();
        assertTrue(board.isGameComplete());

        //Starts a new board and assure it is not complete
        board.initializeBoard();
        assertFalse(board.isGameComplete());
    }

    @Test
    void notifyListenersTest() {
        // Create mock listeners
        BoardUpdateListener listener1 = mock(BoardUpdateListener.class);
        BoardUpdateListener listener2 = mock(BoardUpdateListener.class);

        // Add them to the board
        board.addListener(listener1);
        board.addListener(listener2);

        // Call notifyListeners
        board.notifyListeners();

        // Verify updateBoard called on each listener
        verify(listener1, times(1)).updateBoard();
        verify(listener2, times(1)).updateBoard();
    }

}
