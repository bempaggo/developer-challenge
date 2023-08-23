package facade;

import interfaces.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ControllerTest {

    private Controller controller;
    private Graph board;

    @BeforeEach
    void setUp() {
        board = mock(Graph.class);
        controller = new Controller(board);
    }

    @Test
    void initializeBoardTest() {
        controller.initializeBoard();
        verify(board, times(1)).initializeBoard();
    }

    @Test
    void setBoardAsSolvedTest() {
        controller.setBoardAsSolved();
        verify(board, times(1)).setBoardAsSolved();
    }

    @Test
    void getCellsTest() {
        controller.getCells();
        verify(board, times(1)).getCells();
    }

    @Test
    void swapTest() {
        Integer key = mock(Integer.class);
        controller.keyPressed(key);
        verify(board, times(1)).keyPressed(key);
    }

    @Test
    void clickTest() {
        Integer value = mock(Integer.class);
        controller.buttonClicked(value);
        verify(board, times(1)).buttonClicked(value);
    }

    @Test
    void isGameCompleteTest() {
        controller.isGameComplete();
        verify(board, times(1)).isGameComplete();
    }

}