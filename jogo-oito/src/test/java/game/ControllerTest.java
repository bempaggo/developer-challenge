package game;


import facade.Controller;
import model.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller(3, 3, 123);
        controller.configBoard();
    }

    @Test
    public void testGetMatrix() {
        List<List<Cell>> matrix = controller.getMatrix();
        Assert.assertNotNull(matrix);
        Assert.assertEquals(3, matrix.size());
        Assert.assertEquals(3, matrix.get(0).size());
    }

    @Test
    public void testFindCellByValue() {
        Cell cell = controller.findCellByValue(5);
        Assert.assertNotNull(cell);
        Assert.assertEquals(5, cell.getValue().intValue());
    }

    @Test
    public void testSwapValue() {
        Cell emptyCell = controller.findEmptyCell();
        Cell exchangeCell = controller.findCellByValue(5);

        int emptyCellValue = emptyCell.getValue();
        int exchangeCellValue = exchangeCell.getValue();

        controller.swapValue(emptyCell, exchangeCell);

        Assert.assertEquals(exchangeCellValue, emptyCell.getValue().intValue());
        Assert.assertEquals(emptyCellValue, exchangeCell.getValue().intValue());
    }

    @Test
    public void testFindEmptyCell() {
        Cell emptyCell = controller.findEmptyCell();
        Assert.assertNotNull(emptyCell);
        Assert.assertEquals(0, emptyCell.getValue().intValue());
    }

    @Test
    public void testSwapCells() {
        List<List<Cell>> matrix = controller.getMatrix();
        Cell cell = matrix.get(2).get(2);
        Assert.assertEquals("", cell.valueToText());
        
        matrix = controller.swapCells(-1, 0);
        cell = matrix.get(1).get(2);
        Assert.assertEquals("", cell.valueToText());
               
        matrix = controller.swapCells(1, 0);
        cell = matrix.get(2).get(2);
        Assert.assertEquals("", cell.valueToText());
        
        matrix = controller.swapCells(0, -1);
        cell = matrix.get(2).get(1);
        Assert.assertEquals("", cell.valueToText());
        
        matrix = controller.swapCells(0, 1);
        cell = matrix.get(2).get(2);
        Assert.assertEquals("", cell.valueToText());
    }

}
