package game.facade;

import java.util.List;

import game.interfaces.Vertex;
import game.util.Board;


public class Controller {

    private final Board board;

    public Controller() {
        this.board = new Board();
    }

    public void setting(Boolean feedback, Integer order) {
        this.board.setting(feedback, order);
    }

    public List<Vertex> getCells() {
        return this.board.getCells();
    }

    public void swap(Integer keyCode) {
        this.board.swap(keyCode);
    }

    public Boolean checkVictory() {
        return this.board.checkVictory();
    }

    public void click(Integer cellValue) {
        this.board.click(cellValue);
    }

}
