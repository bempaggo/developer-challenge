package chat.gpt.controller;

import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.model.GridInterface;

public class GameService implements MovementInterface {

    private GridInterface grid;

    public GameService(GridInterface grid) {
        this.grid = grid;
    }

    @Override
    public void moveDown() {
        validateMove(grid.getEmptySlotIndex() >= grid.getGridWidth(), 
                grid.getEmptySlotIndex() - grid.getGridWidth());
    }

    @Override
    public void moveUp() {
        validateMove(grid.getEmptySlotIndex() < grid.getGridSize() - grid.getGridWidth(),
                grid.getEmptySlotIndex() + grid.getGridWidth());
    }

    @Override
    public void moveLeft() {
        validateMove(grid.getEmptySlotIndex() % grid.getGridWidth() != grid.getGridWidth() - 1,
                grid.getEmptySlotIndex() + 1);
    }

    @Override
    public void moveRight() {
        validateMove(grid.getEmptySlotIndex() % grid.getGridWidth() != 0,
                grid.getEmptySlotIndex() - 1);
    }

    private void validateMove(boolean condition, int swapIndex) {
        if (!condition) {
            throw new ImpossibleMoveException();
        }
        swapElements(swapIndex);
    }

    private void swapElements(int index) {
        List<Integer> gridData = grid.getGridData();
        int temp = gridData.get(grid.getEmptySlotIndex());
        gridData.set(grid.getEmptySlotIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

}