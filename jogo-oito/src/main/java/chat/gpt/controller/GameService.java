package chat.gpt.controller;

import static chat.gpt.util.Constants.*;

import java.util.List;

import chat.gpt.model.Grid;

public class GameService {

    private Grid grid;

    public GameService() {
        this.grid = new Grid();
    }

    public GameService(Grid grid) {
        this.grid = grid;
    }

    public void moveDown() {
        if (grid.getEmptyIndex() >= GRID_WIDTH)
            swapElements(grid.getEmptyIndex() - GRID_WIDTH);

    }

    public void moveUp() {
        if (grid.getEmptyIndex() < GRID_AREA - GRID_WIDTH)
            swapElements(grid.getEmptyIndex() + GRID_WIDTH);

    }

    public void moveLeft() {
        if (grid.getEmptyIndex() % GRID_WIDTH != GRID_WIDTH - 1) {
            swapElements(grid.getEmptyIndex() + 1);
        }
    }

    public void moveRight() {
        if (grid.getEmptyIndex() % GRID_WIDTH != 0) {
            swapElements(grid.getEmptyIndex() - 1);
        }
    }

    private void swapElements(int index) {
        List<Integer> gridData = grid.getGrid();
        int temp = gridData.get(grid.getEmptyIndex());
        gridData.set(grid.getEmptyIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

    // controller
    public boolean gameIsComplete() {
        return grid.getGrid().equals(GAME_FINISHED);
    }

    // controller
    public void resetGrid() {
        grid = new Grid();
    }

    // controller
    public List<Integer> gridActualState() {
        return grid.getGrid();
    }
    
}