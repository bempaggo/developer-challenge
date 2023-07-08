package chat.gpt.controller;

import java.util.List;

import chat.gpt.exception.ImpossibleMoveException;
import chat.gpt.model.ListInfoInterface;

public class GameService implements MovementInterface {

    private ListInfoInterface list;

    public GameService(ListInfoInterface list) {
        this.list = list;
    }

    @Override
    public void moveDown() {
        validateMove(list.getEmptySlotIndex() >= list.getGridWidth(), 
                list.getEmptySlotIndex() - list.getGridWidth());
    }

    @Override
    public void moveUp() {
        validateMove(list.getEmptySlotIndex() < list.getGridSize() - list.getGridWidth(),
                list.getEmptySlotIndex() + list.getGridWidth());
    }

    @Override
    public void moveLeft() {
        validateMove(list.getEmptySlotIndex() % list.getGridWidth() != list.getGridWidth() - 1,
                list.getEmptySlotIndex() + 1);
    }

    @Override
    public void moveRight() {
        validateMove(list.getEmptySlotIndex() % list.getGridWidth() != 0,
                list.getEmptySlotIndex() - 1);
    }

    private void validateMove(boolean condition, int swapIndex) {
        if (!condition) {
            throw new ImpossibleMoveException();
        }
        swapElements(swapIndex);
    }

    private void swapElements(int index) {
        List<Integer> gridData = list.getGridData();
        int temp = gridData.get(list.getEmptySlotIndex());
        gridData.set(list.getEmptySlotIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

}