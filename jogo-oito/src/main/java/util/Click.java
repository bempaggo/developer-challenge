package util;

import interfaces.Action;
import interfaces.Graph;
import model.Movement;

public class Click {

    private final Graph board;

    private Click(Graph board) {
        this.board = board;
    }

    public static Click of(Graph board) {
        return new Click(board);
    }

    public void execute(Integer cellValue) {
        Action movement = Movement.of(this.board.getEmptyCell());
        this.board.setEmptyCell(movement.swapCells(cellValue));
    }

}