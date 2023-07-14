package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import interfaces.Vertex;

public class MatrixTest {

    @Test
    void getCells_ReturnListOfCells(){
        int numberOfCellsExpected = 9;
        Matrix matrix = new Matrix();
        Assertions.assertFalse(matrix.getCells().isEmpty());
        Assertions.assertEquals(numberOfCellsExpected, matrix.getCells().size());
    }

}
