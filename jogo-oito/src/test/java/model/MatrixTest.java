package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Vertex;

public class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    void setup(){
        matrix = new Matrix();
    }

    @Test
    void getCells_ReturnListOfCells(){
        int numberOfCellsExpected = 9;
        Assertions.assertFalse(matrix.getCells().isEmpty());
        Assertions.assertEquals(numberOfCellsExpected, matrix.getCells().size());
    }

    @Test
    void defineAdjacent() {
        int numberofRowsExpected = 3;
        Assertions.assertNotNull(matrix.getRows());
        Assertions.assertTrue(matrix.getRows().size() == numberofRowsExpected);
    }
}
