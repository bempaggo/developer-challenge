package facade;

import interfaces.Graph;
import interfaces.Vertex;

import java.util.List;

public record Controller(Graph board) {

    public void setBoardAsSolved() {
        this.board.setBoardAsSolved();
    }

    public void initializeBoard() {
        this.board.initializeBoard();
    }

    public List<Vertex> getCells() {
        return this.board.getCells();
    }

    public void keyPressed(Integer keyCode) {
        this.board.keyPressed(keyCode);
    }

    public void buttonClicked(Integer cellValue) {
        this.board.buttonClicked(cellValue);
    }

    public Boolean isGameComplete() {
        return this.board.isGameComplete();
    }


}
