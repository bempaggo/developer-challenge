package model;

import java.util.List;
import java.util.Optional;

public class MoveRuleset implements MovementInterface {

    private GridInterface grid;
    private MoveValidator moveValidator = new MoveValidator();

    public MoveRuleset() {
    }

    public void setGrid(GridInterface grid) {
        this.grid = grid;
    }

    @Override
    public void move(Integer buttonValue) throws IndexOutOfBoundsException {
        validateMove(buttonValue);
    }

    @Override
    public void moveUp() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() + grid.getGridWidth());
        if (targetValueToSwap != null) {
            move(targetValueToSwap);
        }
    }

    @Override
    public void moveDown() {
        Integer targetValueToSwap = getByIndex(grid.getEmptySlotIndex() - grid.getGridWidth());
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
        Integer valueIndex = grid.getGridData().indexOf(buttonValue);
        
        Optional.of(valueIndex)
                .filter(i -> moveValidator.isValidMove(grid.getEmptySlotIndex(), i))
                .ifPresent(this::swapElements);

    }

    private void swapElements(Integer index) {
        List<Integer> gridData = grid.getGridData();
        Integer emptySlotIndex = grid.getEmptySlotIndex();

        Integer temp = gridData.get(emptySlotIndex);
        gridData.set(emptySlotIndex, gridData.get(index));
        gridData.set(index, temp);
    }

    private Integer getByIndex(Integer index) {
        return grid.getGridData().stream()
                .skip(index)
                .findFirst()
                .orElse(null);
    }
}
