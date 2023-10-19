package service;

import model.Matrix;
import service.interfaces.Cell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import service.interfaces.Graph;
import util.Keyboard;

public class GraphImpl implements Graph {

    private List<Cell> cells;
    private Cell emptyCell;
    private Integer length;
    private Matrix matrix;

    public GraphImpl() {
    }

    @Override
    public void feedback() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.defineEmptyCell();
    }

    @Override
    public void setter() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.shuffleCell();
        this.defineEmptyCell();

    }

    private void shuffleCell() {
        Iterator<Integer> iterator = this.shuffleValues().iterator();
        this.cells
                .forEach(vertex -> vertex.setValue(iterator.next()));
    }

    private List<Integer> shuffleValues() {
        List<Integer> values = new ArrayList<>();
        this.cells.stream()
                .map(Cell::getValue)
                .forEach(values::add);
        Collections.shuffle(values);
        return values;
    }

    private void defineEmptyCell() {
        Optional<Cell> minCell = this.cells.stream()
                .min(Comparator.comparing(Cell::getValue));
        minCell.ifPresent(this::accept);
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
    public List<Cell> getCells() {
        return this.cells;
    }

    @Override
    public Cell getEmptyCell() {
        return this.emptyCell;
    }

    @Override
    public Boolean checkGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);

    }

    private void accept(Cell cell) {
        this.emptyCell = cell;
    }
}
