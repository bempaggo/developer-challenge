package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.List;

public class Game {

    private Grid grid;

    public Game() {
        this.grid = new Grid();
    }

    public Game(Grid grid) {
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

    public boolean gameIsComplete() {
        return grid.getGrid().equals(GAME_FINISHED);
    }

    public void resetGrid() {
        grid = new Grid();
    }

    public List<Integer> gridActualState() {
        return grid.getGrid();
    }
    
}