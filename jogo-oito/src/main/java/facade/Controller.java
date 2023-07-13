package facade;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.List;
import util.Board;

public class Controller {

    private final Graph board;

    public Controller(Graph graph) {
        this.board = graph;
    }
    
    public void feedback() {
        this.board.feedback();
    }

    public void setting() {
        this.board.setting();
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

    public void click(Integer cellValue) {
        this.board.click(cellValue);
    }

}
