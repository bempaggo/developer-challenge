package chat.gpt.controller;

import java.util.List;

import chat.gpt.model.ListInfoInterface;

public class GameService {
    
    private ListInfoInterface list;
    
    public GameService(ListInfoInterface list) {
        this.list = list;
    }

    public void moveDown() {
        if (list.getEmptySlotIndex() >= list.getGridWidth())
            swapElements(list.getEmptySlotIndex() - list.getGridWidth());

    }

    public void moveUp() {
        if (list.getEmptySlotIndex() < list.getGridSize() - list.getGridWidth())
            swapElements(list.getEmptySlotIndex() + list.getGridWidth());

    }

    public void moveLeft() {
        if (list.getEmptySlotIndex() % list.getGridWidth() != list.getGridWidth() - 1) {
            swapElements(list.getEmptySlotIndex() + 1);
        }
    }

    public void moveRight() {
        if (list.getEmptySlotIndex() % list.getGridWidth() != 0) {
            swapElements(list.getEmptySlotIndex() - 1);
        }
    }

    private void swapElements(int index) {
        List<Integer> gridData = list.getGridData();
        int temp = gridData.get(list.getEmptySlotIndex());
        gridData.set(list.getEmptySlotIndex(), gridData.get(index));
        gridData.set(index, temp);
    }

}