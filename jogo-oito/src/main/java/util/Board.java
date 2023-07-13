package util;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import model.Keyboard;
import model.Matrix;

public class Board implements Graph {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private Integer length;
    private Matrix matrix;

    public Board() {
    }

    @Override
    public void feedback() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.defineEmptyCell();
    }

    @Override
    public void setting() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.shuffleCell();
        this.defineEmptyCell();

    }

    private void shuffleCell() {
        Iterator<Integer> iterator = this.shuffleValues().iterator();
        this.cells.stream()
                .forEach(vertex -> vertex.setValue(iterator.next()));
    }

    private List<Integer> shuffleValues() {
        List<Integer> values = new ArrayList<>();
        this.cells.stream()
                .map(Vertex::getValue)
                .forEach(values::add);
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
    public void click(Integer cellValue) {
        this.emptyCell = this.emptyCell.swapCells(cellValue);
    }

    @Override
    public void swap(Integer keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        this.emptyCell = this.emptyCell.click(key);
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
    public Boolean checkVictory() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);

    }

}
