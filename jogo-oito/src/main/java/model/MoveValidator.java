package model;

public class MoveValidator {

    public MoveValidator() {
    }

    public Boolean isValidMove(Integer emptySlotIndex, Integer valueIndex) {
        Coordinates valueToSwap = new Coordinates(valueIndex);
        Coordinates emptySlot = new Coordinates(emptySlotIndex);

        return isAdjacent(emptySlot, valueToSwap);
    }

    private Boolean isAdjacent(Coordinates emptySlot, Coordinates valueToSwap) {
        return Math.abs(emptySlot.getRow() - valueToSwap.getRow()) == 1 &&
               emptySlot.getCol().equals(valueToSwap.getCol()) ||
               Math.abs(emptySlot.getCol() - valueToSwap.getCol()) == 1 &&
               emptySlot.getRow().equals(valueToSwap.getRow());
    }
}