package util;

import interfaces.Graph;
import interfaces.Vertex;
import model.Matrix;

import java.util.*;

public class Board implements Graph {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private Matrix matrix;

    public Board() {
    }

    @Override
    public void feedback() {
        this.resetMatrix();
        this.defineEmptyCell();
    }

    @Override
    public void setting() {
        this.resetMatrix();
        this.shuffleCell();
        this.defineEmptyCell();
    }

    private void resetMatrix() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
    }

    private void shuffleCell() {
        List<Integer> shuffledValues = this.shuffleValues();
        for (int index = 0; index < this.cells.size(); index++) {
            Vertex cell = this.cells.get(index);
            cell.setValue(shuffledValues.get(index));
        }
    }

    private List<Integer> shuffleValues() {
        List<Integer> values = new ArrayList<>();
        for (int index = 0; index < this.cells.size(); index++) {
            Integer value = this.cells.get(index).getValue();
            values.add(value);
        }
        Collections.shuffle(values);
        return values;
    }

    private void defineEmptyCell() {
        Optional<Vertex> minCell = this.cells.stream()
                .min(Comparator.comparing(cell -> cell.getValue()));
        minCell.ifPresent(cell -> {
            this.emptyCell = cell;
        });
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public Vertex getEmptyCell() {
        return this.emptyCell;
    }

    @Override
    public void setEmptyCell(Vertex cell) {
        this.emptyCell = cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return cells.equals(board.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
    }

}
