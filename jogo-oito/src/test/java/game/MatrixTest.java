package game;

import model.Matrix;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MatrixTest {

    @Test
    void testGetCells() {
        Matrix matrix = new Matrix();

        assertNotNull(matrix.getCells());
        assertThat(matrix.getCells())
                .hasSize(9)
                .extracting("value")
                .containsExactly(1 ,2, 3, 4, 5, 6, 7, 8, 0);
    }

}
