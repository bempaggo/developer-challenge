package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Arrays;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.EmptyPositionNotFoundException;

public class Game {

    private Grid grid;

    public Game() {
        this.grid = new Grid();
    }
    
    public Game(Grid grid) {
        this.grid = grid;
    }

    public void move(int[] coordinates) {

        int rowCoordinate = coordinates[0];
        int columnCoordinate = coordinates[1];

        int[] emptyPosition = findEmptyPosition();
        int emptyRow = emptyPosition[0];
        int emptyColumn = emptyPosition[1];

        int newRow = emptyRow + rowCoordinate;
        int newColumn = emptyColumn + columnCoordinate;

        if (validPosition(newRow, newColumn)) {
            changePositions(emptyRow, emptyColumn, newRow, newColumn);
        } else
            throw new ImpossibleMoveException();
    }

    private int[] findEmptyPosition() {
        int[][] gridData = grid.getGrid();

        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                if (gridData[i][j] == 0)
                    return new int[] { i, j };
            }
        }

        throw new EmptyPositionNotFoundException();
    }

    private boolean validPosition(int row, int column) {
        return (row >= 0 && row < GRID_WIDTH) &&
                (column >= 0 && column < GRID_LENGTH);

    }

    private void changePositions(int row1, int column1, int row2, int column2) {
        int[][] gridData = grid.getGrid();
        int temp = gridData[row1][column1];
        gridData[row1][column1] = gridData[row2][column2];
        gridData[row2][column2] = temp;
    }

    public boolean gameIsComplete() {
        return Arrays.deepEquals(grid.getGrid(), GAME_FINISHED);
    }

    public void resetGame() {
        grid = new Grid();
    }

    public void loadGrid(int[][] initialGrid) {
        grid = new Grid(initialGrid);
    }

    public int[][] gridActualState() {
        int[][] originalGrid = grid.getGrid();
        return Arrays.stream(originalGrid)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }

}
