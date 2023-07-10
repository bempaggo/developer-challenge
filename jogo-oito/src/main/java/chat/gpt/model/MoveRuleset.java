package chat.gpt.model;

import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;

public class MoveRuleset implements MovementInterface {

    private GridInterface grid;
    private ValidateMove validate = new ValidateMove();

    public MoveRuleset(GridInterface grid) {
        this.grid = grid;
    }

    @Override
    public void move(int buttonValue) throws IndexOutOfBoundsException {
        List<Integer> gridData = grid.getGridData();
        int emptySlotIndex = grid.getEmptySlotIndex();
        int valueIndex = gridData.indexOf(buttonValue);

        boolean isValidMove = validate.delta(emptySlotIndex, valueIndex) ||
                validate.adjacentDelta(emptySlotIndex, valueIndex);

        validate.move(isValidMove, valueIndex);
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

    private void swapElements(int index) {
        List<Integer> gridData = grid.getGridData();
        int temp = gridData.get(grid.getEmptySlotIndex());
        gridData.set(grid.getEmptySlotIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

    private class ValidateMove {
        // TODO: implementar classe interna de validação para retirar validações da classe principal

        void move(boolean condition, int swapIndex) {
            if (!condition) {
                throw new ImpossibleMoveException();
            }
            swapElements(swapIndex);
        }

        boolean delta(int emptySlotIndex, int valueIndex) {
            int gridSize = grid.getGridSize();
            int sqrtN = (int) Math.sqrt(gridSize);
            int rowEmpty = emptySlotIndex / sqrtN;
            int rowValue = valueIndex / sqrtN;
            int colEmpty = emptySlotIndex % sqrtN;
            int colValue = valueIndex % sqrtN;
            return Math.abs(rowEmpty - rowValue) + Math.abs(colEmpty - colValue) == 1;
        }

        private boolean adjacentDelta(int emptySlotIndex, int valueIndex) {
            int gridSize = grid.getGridSize();
            int sqrtN = (int) Math.sqrt(gridSize);
            int rowEmpty = emptySlotIndex / sqrtN;
            int rowValue = valueIndex / sqrtN;
            int colEmpty = emptySlotIndex % sqrtN;
            int colValue = valueIndex % sqrtN;
            return Math.abs(rowEmpty - rowValue) == 1 && colEmpty == colValue
                    || Math.abs(colEmpty - colValue) == 1 && rowEmpty == rowValue;
        }
    }

}