package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.enumType.Keyboard;
import service.Edge;
import service.Vertex;
import service.serviceImpl.Cell;

public class CellTest {
	
    private Vertex cell1;
    private Vertex cell2;

    @BeforeEach
    public void setUp() {
        cell1 = new Cell(1);
        cell2 = new Cell(2);
    }

    @Test
    @DisplayName("Test creating horizontal adjacent")
    public void testCreatingHorizontalAdjacent() {
    	
        cell1.creatingHorizontalAdjacent(cell2);
        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    @DisplayName("Test creating vertical adjacent")
    public void testCreatingVerticalAdjacent() {
    	
        cell1.creatingVerticalAdjacent(cell2);
        assertEquals(1, cell1.getAdjacents().size());
        assertEquals(1, cell2.getAdjacents().size());
    }

    @Test
    @DisplayName("Test click")
    public void testClick() {
        cell1.creatingHorizontalAdjacent(cell2);

        assertEquals(cell2, cell1.click(Keyboard.LEFT));
    }

    @Test
    @DisplayName("Test swap cells")
    public void testSwapCells() {
    	
    	 Vertex cell1 = new Cell(0);
         Vertex cell2 = new Cell(2);
         cell1.creatingHorizontalAdjacent(cell2);
         cell1.swapCells(cell2.getValue());
         assertEquals(2, cell1.getValue());
         assertEquals(0, cell2.getValue());
    }

    @Test
    @DisplayName("Test creating horizontal adjacent")
    public void defineAdjacents() {
    	
    	Vertex cell1 = new Cell(5);
        Vertex cell2 = new Cell(10);
        Vertex cell3 = new Cell(15);
        Vertex cell4 = new Cell(20);

        cell1.creatingVerticalAdjacent(cell2);
        cell1.creatingVerticalAdjacent(cell3);
        cell1.creatingHorizontalAdjacent(cell4);

        List<Edge> adjacents = cell1.getAdjacents();
        assertEquals(cell2, adjacents.get(0).getCell());
        assertEquals(cell3, adjacents.get(1).getCell());
        assertEquals(cell4, adjacents.get(2).getCell());
    }
}