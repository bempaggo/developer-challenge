package model;

import java.util.Optional;

public class MoveRuleset implements MovementInterface {

    private BoardInterface board;
    private MoveValidator moveValidator = new MoveValidator();

    public MoveRuleset() {
    }

    public void setBoard(BoardInterface board) {
        this.board = board;
    }

    @Override
    public void move(Integer buttonValue) throws IndexOutOfBoundsException {
        validateMove(buttonValue);
    }

    @Override
    public void moveUp() {
        Integer targetValueToSwap = getByIndex(board.getEmptySlotIndex() + board.getBoardWidth());
        move(targetValueToSwap);
    }

    @Override
    public void moveDown() {
        Integer targetValueToSwap = getByIndex(board.getEmptySlotIndex() - board.getBoardWidth());
        move(targetValueToSwap);

    }

    @Override
    public void moveLeft() {
        Integer targetValueToSwap = getByIndex(board.getEmptySlotIndex() + 1);
        move(targetValueToSwap);
    }

    @Override
    public void moveRight() {
        Integer targetValueToSwap = getByIndex(board.getEmptySlotIndex() - 1);
        move(targetValueToSwap);
    }

    private void validateMove(Integer buttonValue) {
        Integer valueIndex = board.getBoardData().indexOf(buttonValue);

        Optional.of(valueIndex)
                .filter(i -> moveValidator.isValidMove(board.getEmptySlotIndex(), i))
                .ifPresent(board::swapElements);

    }

    public Integer getByIndex(Integer index) {
        try {
            return board.getBoardData().stream()
                    .skip(index)
                    .findFirst()
                    .orElse(null);
        } catch (IndexOutOfBoundsException e) {
            // Interrompe o fluxo da aplicação apenas
        }
        return null;
    }
}
