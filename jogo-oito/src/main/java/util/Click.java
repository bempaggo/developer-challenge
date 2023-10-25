package util;

import command.Command;
import interfaces.Action;
import interfaces.Graph;
import interfaces.Vertex;
import model.Movement;

public class Click implements Command {

    private final Graph board;

    private Click(Graph board) {
        this.board = board;
    }

    public static Click of(Graph board) {
        return new Click(board);
    }

    public void execute() {
        Action movement = Movement.of(this.board.getEmptyCell());
        Vertex currentCell = movement.swapCells(this.board.getCurrentCellValue());
        this.board.setEmptyCell(currentCell);
    }

}