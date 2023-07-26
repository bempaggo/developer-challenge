package game;

import interfaces.Graph;
import interfaces.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;
import util.Click;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickTest {

    private Click click;
    private Graph board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.feedback();
        click = Click.of(board);
    }

    @Test
    void testClick() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer cellValue = cell.getValue();

        click.execute(cellValue);

        assertEquals(cellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }
}