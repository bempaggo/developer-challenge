package util;

import command.Command;
import interfaces.Action;
import interfaces.Graph;
import interfaces.Vertex;
import model.Movement;

public class SwapCell implements Command {

    private final Graph board;

    private SwapCell(Graph board) {
        this.board = board;
    }

    public static SwapCell of(Graph board) {
        return new SwapCell(board);
    }

    public void execute() {
        Action movement = Movement.of(this.board.getEmptyCell());
        Vertex currentCell = movement.swapCells(this.board.getCurrentCellValue());
        this.board.setEmptyCell(currentCell);
    }

}