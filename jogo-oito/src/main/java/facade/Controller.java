package facade;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.List;
import util.Board;

public class Controller {

    private final Graph board;
    private final Boolean feedback;

    public Controller() {
        this.feedback = Boolean.FALSE;
        this.board = new Board(this.feedback);
    }

    public void configBoard() {
        this.board.loadCells();
        this.board.defineCellRelationships();
    }

    public List<Vertex> getCells() {
        return this.board.getCells();
    }

    public void swap(Integer keyCode) {
        this.board.swap(keyCode);
    }

    public Boolean checkGameOver() {
        return this.board.checkGameOver();

    }

    public void click(Integer cellValue ) {
        this.board.click(cellValue);
    }

}
