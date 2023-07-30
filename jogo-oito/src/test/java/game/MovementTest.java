package game;

import interfaces.Vertex;
import model.Cell;
import model.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementTest {

    private Movement movement;
    private Vertex emptyCell;

    @BeforeEach
    void setUp() {
        emptyCell = Cell.of(0);
        movement = Movement.of(emptyCell);
    }

    @Test
    void testSwapCells() {
        Vertex cell = Cell.of(2);
        cell.createHorizontalAdjacent(emptyCell);

        assertEquals(2, cell.getValue());
        assertEquals(0, emptyCell.getValue());

        movement.swapCells(cell.getValue());

        assertEquals(0, cell.getValue());
        assertEquals(2, emptyCell.getValue());
    }

}
