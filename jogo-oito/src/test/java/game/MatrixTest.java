package game;

import model.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatrixTest {

    @Test
    void testgetCells() {
        Matrix matrix = new Matrix();

        assertNotNull(matrix.getCells());
        assertEquals(matrix.getCells().size(), 9);
    }

}
