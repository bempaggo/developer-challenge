package chat.gpt.controller;

import java.util.List;

import chat.gpt.model.Grid;
import chat.gpt.util.Constants;

public class GameService {
    
    private Grid grid;
    
    public GameService() {
    }
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void moveDown() {
        if (grid.getEmptyIndex() >= Constants.GRID_WIDTH.getMeasure())
            swapElements(grid.getEmptyIndex() - Constants.GRID_WIDTH.getMeasure());

    }

    public void moveUp() {
        if (grid.getEmptyIndex() < Constants.GRID_SIZE.getMeasure() - Constants.GRID_WIDTH.getMeasure())
            swapElements(grid.getEmptyIndex() + Constants.GRID_WIDTH.getMeasure());

    }

    public void moveLeft() {
        if (grid.getEmptyIndex() % Constants.GRID_WIDTH.getMeasure() != Constants.GRID_WIDTH.getMeasure() - 1) {
            swapElements(grid.getEmptyIndex() + 1);
        }
    }

    public void moveRight() {
        if (grid.getEmptyIndex() % Constants.GRID_WIDTH.getMeasure() != 0) {
            swapElements(grid.getEmptyIndex() - 1);
        }
    }

    private void swapElements(int index) {
        List<Integer> gridData = grid.getGridData();
        int temp = gridData.get(grid.getEmptyIndex());
        gridData.set(grid.getEmptyIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

    
}