package game.facade;

import java.util.List;

import game.interfaces.Vertex;
import game.model.Matrix;

public class Controller {

    private final Matrix matrix;

    public Controller() {
        this.matrix = new Matrix();
    }

    public void setting(Boolean feedback) {
        this.matrix.createCells(feedback);
    }

    public List<Vertex> getCells() {
        return this.matrix.getCells();
    }

    public void swap(Integer keyCode) {
        this.matrix.swap(keyCode);
    }

    public Boolean checkVictory() {
        return this.matrix.checkVictory();
    }

    public void click(Integer cellValue) {
        this.matrix.click(cellValue);
    }

}
