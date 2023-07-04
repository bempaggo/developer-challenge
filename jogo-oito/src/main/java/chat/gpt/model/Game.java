package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Arrays;
import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.exception.PositionNotFoundException;

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

        List<Integer> gridData = grid.getGrid();
        int emptyIndex = gridData.indexOf(EMPTY);
        int emptyRow = emptyIndex / GRID_WIDTH;
        int emptyColumn = emptyIndex % GRID_WIDTH;

        int newRow = emptyRow + rowCoordinate;
        int newColumn = emptyColumn + columnCoordinate;

        if (validPosition(newRow, newColumn)) {
            changePositions(emptyIndex, newRow * GRID_WIDTH + newColumn);
        } else {
            throw new ImpossibleMoveException();
        }
    }

    public int[] findPosition(int label) {
        List<Integer> gridData = grid.getGrid();
        int index = gridData.indexOf(label);

        if (index != -1) {
            int row = index / GRID_WIDTH;
            int column = index % GRID_WIDTH;
            return new int[]{row, column};
        }

        throw new PositionNotFoundException();
    }

    public boolean validPosition(int row, int column) {
        return (row >= 0 && row < GRID_LENGTH) && (column >= 0 && column < GRID_WIDTH);
    }

    public void changePositions(int index1, int index2) {
        List<Integer> gridData = grid.getGrid();
        int temp = gridData.get(index1);
        gridData.set(index1, gridData.get(index2));
        gridData.set(index2, temp);
    }

    public boolean gameIsComplete() {
        List<Integer> gridData = grid.getGrid();
        return gridData.equals(Arrays.asList(GAME_FINISHED));
    }

    public void resetGrid() {
        grid = new Grid();
    }


    public int[][] gridActualState() {
    List<Integer> gridData = grid.getGrid();
    int[][] gridArray = new int[GRID_LENGTH][GRID_WIDTH];
    
    for (int i = 0; i < GRID_LENGTH; i++) {
        for (int j = 0; j < GRID_WIDTH; j++) {
            int index = i * GRID_WIDTH + j;
            gridArray[i][j] = gridData.get(index);
        }
    }
    
    return gridArray;
}
}
