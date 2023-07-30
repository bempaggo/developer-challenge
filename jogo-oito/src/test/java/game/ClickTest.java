package game;

import command.Command;
import interfaces.Graph;
import interfaces.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Board;
import util.Click;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClickTest {

    private Command click;
    private Graph board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.feedback();
        click = Click.of(board);
    }

    @Test
    void testExecute() {
        Vertex emptyCell = board.getEmptyCell();
        Vertex cell = board.getCells().get(7);
        Integer currentCellValue = cell.getValue();
        board.setCurrentCellValue(currentCellValue);

        click.execute();

        assertEquals(currentCellValue, emptyCell.getValue());
        assertEquals(0, cell.getValue());
    }

}