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

    public Graph getBoard() {
        return board;
    }

    public void gameSolutionBoardState() {
        this.board.gameSolutionBoardState();
    }

    public void gameStartBoardState() {
        this.board.gameStartBoardState();
    }

    public List<Vertex> getCells() {
        return this.board.getCells();
    }

    public void swap(Integer keyCode) {
        this.board.swap(keyCode);
    }

    public Boolean isGameComplete() {
        return this.board.isGameComplete();
    }

    public void click(Integer cellValue) {
        this.board.click(cellValue);
    }

}
