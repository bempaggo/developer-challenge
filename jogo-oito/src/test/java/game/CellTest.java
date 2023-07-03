package game;

import model.Cell;
import org.junit.Assert;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author allen
 */

public class CellTest {

    @Test
    public void testSetValue() {
        Cell cell = new Cell(0, 0, 0);
        cell.setValue(5);
        Assert.assertEquals(5, cell.getValue().intValue());
    }

    @Test
    public void testValueToText() {
        Cell cell1 = new Cell(0, 0, 0);
        Assert.assertEquals("", cell1.valueToText());

        Cell cell2 = new Cell(0, 0, 7);
        Assert.assertEquals("7", cell2.valueToText());
    }

    @Test
    public void testGetIndexRow() {
        Cell cell = new Cell(2, 3, 0);
        Assert.assertEquals(2, cell.getIndexRow());
    }

    @Test
    public void testGetIndexColumn() {
        Cell cell = new Cell(2, 3, 0);
        Assert.assertEquals(3, cell.getIndexColumn());
    }

    @Test
    public void testGetValidPosition() {
        Cell cell = new Cell(0, 0, 0);
        cell.setValidPosition(true);
        Assert.assertTrue(cell.getValidPosition());
    }

    @Test
    public void testToString() {
        Cell cell = new Cell(2, 3, 7);
        cell.setValidPosition(true);
        String expected = "Cell{indexRow=2, indexColumn=3, value=7, validPosition=true}";
        Assert.assertEquals(expected, cell.toString());
    }
}
