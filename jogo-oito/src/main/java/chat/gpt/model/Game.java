package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;

public class Game {

    private Grid grid;

    public Game() {
        this.grid = new Grid();
    }

    public Game(Grid grid) {
        this.grid = grid;
    }

    public void moveDown() {
        if (grid.getEmptyIndex() >= GRID_WIDTH) {
            swapElements(grid.getEmptyIndex() - GRID_WIDTH);
        }
    }

    public void moveUp() {
        if (grid.getEmptyIndex() < GRID_AREA - GRID_WIDTH) {
            swapElements(grid.getEmptyIndex() + GRID_WIDTH);
        }
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

    public void move(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP -> moveUp();
            case KeyEvent.VK_DOWN -> moveDown();
            case KeyEvent.VK_LEFT -> moveLeft();
            case KeyEvent.VK_RIGHT -> moveRight();
            default -> throw new ImpossibleMoveException();
        }
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