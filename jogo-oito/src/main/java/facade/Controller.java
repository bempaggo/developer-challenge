package facade;

import java.util.List;
import model.Board;
import model.Cell;

public class Controller {

    private final Board board;
    private final Boolean feedback;

    public Controller() {
        this.feedback = Boolean.FALSE;
        this.board = new Board(this.feedback);
    }

    public void configBoard() {
        this.board.loadCells();
        this.board.defineCellRelationships();
    }

    public List<Cell> getCells() {
        return this.board.getCells();
    }

    public void swap(Integer value) {
        this.board.swap(value);
    }

    public Boolean checkGameOver() {
        return this.board.checkGameOver();

    }

    public void click(Integer keyCode) {
        this.board.click(keyCode);
    }

}
