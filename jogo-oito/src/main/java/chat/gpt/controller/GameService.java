package chat.gpt.controller;

import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.model.GridInterface;

public class GameService implements MovementInterface {

    private GridInterface grid;

    public GameService(GridInterface grid) {
        this.grid = grid;
    }

    public void move(int value) {
        List<Integer> gridData = grid.getGridData();
        int emptySlotIndex = grid.getEmptySlotIndex();
        int valueIndex = gridData.indexOf(value);

        boolean isValidMove = validateDelta(emptySlotIndex, valueIndex) ||
                validateAdjacentDelta(emptySlotIndex, valueIndex);

        validateMove(isValidMove, valueIndex);
    }

    @Override
    public void moveUp() {
        try {
            int value = grid.getGridData().get(grid.getEmptySlotIndex() + grid.getGridWidth());
            move(value);
        } catch (IndexOutOfBoundsException e) {
            // Tratar a exceção
        }

    }

    @Override
    public void moveDown() {
        try {
            int value = grid.getGridData().get(grid.getEmptySlotIndex() - grid.getGridWidth());
            move(value);
        } catch (IndexOutOfBoundsException e) {
            // Tratar a exceção
        }
    }

    @Override
    public void moveLeft() {
        try {
            int value = grid.getGridData().get(grid.getEmptySlotIndex() + 1);
            move(value);
        } catch (IndexOutOfBoundsException e) {
            // Tratar a exceção
        }
    }

    @Override
    public void moveRight() {
        try {
            int value = grid.getGridData().get(grid.getEmptySlotIndex() - 1);
            move(value);
        } catch (IndexOutOfBoundsException e) {
            // Tratar a exceção
        }
    }

    private void validateMove(boolean condition, int swapIndex) {
        if (!condition) {
            throw new ImpossibleMoveException();
        }
        swapElements(swapIndex);
    }

    private boolean validateDelta(int emptySlotIndex, int valueIndex) {
        int gridSize = grid.getGridSize();
        int sqrtN = (int) Math.sqrt(gridSize);
        int rowEmpty = emptySlotIndex / sqrtN;
        int rowValue = valueIndex / sqrtN;
        int colEmpty = emptySlotIndex % sqrtN;
        int colValue = valueIndex % sqrtN;
        return Math.abs(rowEmpty - rowValue) + Math.abs(colEmpty - colValue) == 1;
    }
    
    private boolean validateAdjacentDelta(int emptySlotIndex, int valueIndex) {
        int gridSize = grid.getGridSize();
        int sqrtN = (int) Math.sqrt(gridSize);
        int rowEmpty = emptySlotIndex / sqrtN;
        int rowValue = valueIndex / sqrtN;
        int colEmpty = emptySlotIndex % sqrtN;
        int colValue = valueIndex % sqrtN;
        return Math.abs(rowEmpty - rowValue) == 1 && colEmpty == colValue
                || Math.abs(colEmpty - colValue) == 1 && rowEmpty == rowValue;
    }
    

    private void swapElements(int index) {
        List<Integer> gridData = grid.getGridData();
        int temp = gridData.get(grid.getEmptySlotIndex());
        gridData.set(grid.getEmptySlotIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

}