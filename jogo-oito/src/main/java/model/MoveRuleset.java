package model;

import java.util.List;
import java.util.Optional;

public class MoveRuleset implements MovementInterface {

    private BoardInterface grid;
    private MoveValidator moveValidator = new MoveValidator();

    public MoveRuleset() {
    }

    public void setGrid(BoardInterface grid) {
        this.grid = grid;
    }

    @Override
    public void move(Integer buttonValue) throws IndexOutOfBoundsException {
        validateMove(buttonValue);
    }

    @Override
    public void moveUp() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() + grid.getBoardWidth());
        if (targetValueToSwap != null) {
            move(targetValueToSwap);
        }
    }

    @Override
    public void moveDown() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() - grid.getBoardWidth());
        move(targetValueToSwap);
    }

    @Override
    public void moveLeft() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() + 1);
        move(targetValueToSwap);    
    }

    @Override
    public void moveRight() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() - 1);
        move(targetValueToSwap);
    }

    private void validateMove(Integer buttonValue) {
        Integer valueIndex = grid.getBoardData().indexOf(buttonValue);
        
        Optional.of(valueIndex)
                .filter(i -> moveValidator.isValidMove(grid.getEmptySlotIndex(), i))
                .ifPresent(this::swapElements);

    }

    private void swapElements(Integer index) {
        List<Integer> gridData = grid.getBoardData();
        Integer emptySlotIndex = grid.getEmptySlotIndex();

        Integer temp = gridData.get(emptySlotIndex);
        gridData.set(emptySlotIndex, gridData.get(index));
        gridData.set(index, temp);
    }

    private Integer getByIndex(Integer index) {
        return grid.getBoardData().stream()
                .skip(index)
                .findFirst()
                .orElse(null);
    }
}
