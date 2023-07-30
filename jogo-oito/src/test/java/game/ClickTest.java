package game;

import interfaces.Graph;
import interfaces.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;
import util.SwapCell;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwapCellTest {

    private SwapCell swapCell;
    private Graph board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.feedback();
        swapCell = SwapCell.of(board);
    }

    @Test
    void testClick() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer currentCellValue = cell.getValue();
        board.setCurrentCellValue(currentCellValue);

        swapCell.execute();

        assertEquals(currentCellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }
}