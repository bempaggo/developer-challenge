package facade;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.List;
import util.Board;

public class Controller {

    private final Graph board;

    public Controller() {
        this.board = new Board();
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

    public Boolean checkGameOver() {
        return this.board.checkGameOver();
    }

    public void click(Integer cellValue) {
        this.board.click(cellValue);
    }

}
