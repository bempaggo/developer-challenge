package game.util;

import java.util.List;
import java.util.stream.IntStream;

import game.enums.Keyboard;
import game.interfaces.Graph;
import game.interfaces.Vertex;
import game.model.Matrix;

public class Board implements Graph {

    private List<Vertex> cells;
    private Integer length;
    private Matrix matrix;

    public Board() {
    }

    @Override
    public void setting(Boolean feedback, Integer order) {
        this.matrix = new Matrix();
        this.matrix.createCells(feedback, order);
        this.cells = this.matrix.getCells();
        this.length = cells.size();
    }

    @Override
    public void click(Integer cellValue) {
        this.matrix.setEmptyCell(this.matrix.getEmptyCell().swapCells(cellValue));
    }

    @Override
    public void swap(Integer keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        this.matrix.setEmptyCell(this.matrix.getEmptyCell().click(key));
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public Vertex getEmptyCell() {
        return this.matrix.getEmptyCell();
    }

    @Override
    public Boolean checkVictory() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);
    }

}
