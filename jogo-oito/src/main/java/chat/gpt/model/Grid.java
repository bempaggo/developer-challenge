package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Arrays;

import chat.gpt.exception.GridDoesNotFeatStandardsException;

public class Grid {

    private int[][] grid;

    public Grid(int[][] gridMode) {
        gridValidate(gridMode);
        this.grid = new int[GRID_LENGTH][GRID_WIDTH];
        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                this.grid[i][j] = gridMode[i][j];
            }
        }
    }

    public Grid() {
        this(DEFAULT_MODE);
    }

    public int[][] getGrid() {
        return grid;
    }

    private void gridValidate(int[][] grid) {
        if (!validSize(grid) || !noRepetedElements(grid)) {
            throw new GridDoesNotFeatStandardsException();
        }
    }

    private boolean validSize(int[][] grid) {
        if (grid.length != GRID_LENGTH) {
            return false;
        }
        for (int[] row : grid) {
            if (row.length != GRID_WIDTH) {
                return false;
            }
        }
        return true;
    }

    private boolean noRepetedElements(int[][] grid) {
        return Arrays.stream(grid)
                .flatMapToInt(Arrays::stream)
                .distinct()
                .count() == GRID_AREA;
    }

}
