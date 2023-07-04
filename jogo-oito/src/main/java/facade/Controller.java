package facade;

import java.util.List;
import model.Board;
import model.Cell;

public class Controller {

    private final Board board;
    private final Integer seed;

    public Controller() {
        this.seed = 0; // seed = 0: aleatório, seed < 0: gabarito, seed > 0: alimentado por semente
        this.board = new Board(this.seed);
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

    public boolean checkGameOver() {
        return this.board.checkGameOver();

    }

    public void clickUp() {
        this.board.clickUp();
    }

    public void clickDown() {
        this.board.clickDown();
    }

    public void clickLeft() {
        this.board.clickLeft();
    }

    public void clickRight() {
        this.board.clickRight();
    }

}
