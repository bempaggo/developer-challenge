package util;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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
    public void showSolvedBoard() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.defineEmptyCell();
    }

    @Override
    public void setUpNewBoard() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.shuffleCells();
        this.defineEmptyCell();

    }

    private void shuffleCells() {
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

    // não é necessário chamar Optional
    private void defineEmptyCell() {
        this.emptyCell = cells.stream()
                .min(Comparator.comparing(Vertex::getValue))
                .orElse(null);
    }

    @Override
    public void moveWithCellValue(Integer cellValue) {
        Vertex cell = this.emptyCell.getAdjacentByValue(cellValue);
        if (cell != null) this.emptyCell = this.emptyCell.swapCells(cell);
    }

    @Override
    public void moveWithCellKey(Integer keyCode) {
        Vertex cell = this.emptyCell.getAdjacentByKeyCode(Keyboard.fromValue(keyCode));
        if (cell != null) this.emptyCell = this.emptyCell.swapCells(cell);
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    // esse método só é usado nos testes
    public Vertex getEmptyCell() {
        return this.emptyCell;
    }

    @Override
    public Boolean checkGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);

    }

}
