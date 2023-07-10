package chat.gpt.model;

import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;

public class MoveRuleset implements MovementInterface {

    private GridInterface grid;

    public MoveRuleset(GridInterface grid) {
        this.grid = grid;
    }

    @Override
    public void move(int buttonValue) throws IndexOutOfBoundsException {
        List<Integer> gridData = grid.getGridData();
        int emptySlotIndex = grid.getEmptySlotIndex();
        int valueIndex = gridData.indexOf(buttonValue);

        boolean isValidMove = validateDelta(emptySlotIndex, valueIndex) ||
                validateAdjacentDelta(emptySlotIndex, valueIndex);

        validateMove(isValidMove, valueIndex);
    }

    @Override
    public void moveUp() throws IndexOutOfBoundsException {
        int value = grid.getGridData().get(grid.getEmptySlotIndex() + grid.getGridWidth());
        move(value);

    }

    @Override
    public void moveDown() throws IndexOutOfBoundsException {
        int value = grid.getGridData().get(grid.getEmptySlotIndex() - grid.getGridWidth());
        move(value);
    }

    @Override
    public void moveLeft() throws IndexOutOfBoundsException {
        int value = grid.getGridData().get(grid.getEmptySlotIndex() + 1);
        move(value);
    }

    @Override
    public void moveRight() throws IndexOutOfBoundsException {
        int value = grid.getGridData().get(grid.getEmptySlotIndex() - 1);
        move(value);
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