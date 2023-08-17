package util;

import factories.GameFactory;
import factories.GameFactoryImpl;
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
    private Matrix matrix;
    private final GameFactory gameFactory = new GameFactoryImpl();

    public Board() {
    }

    @Override
    public void gameSolutionBoardState() {
        this.matrix = gameFactory.createMatrix();
        this.cells = this.matrix.getCells();
        this.defineEmptyCell();
    }

    @Override
    public void gameStartBoardState() {
        this.matrix = gameFactory.createMatrix();
        this.cells = this.matrix.getCells();
        this.shuffleCells();
        this.defineEmptyCell();

    }

    private void shuffleCells() {
        Iterator<Integer> iterator = this.shuffleValues().iterator();
        this.cells.forEach(vertex -> vertex.setValue(iterator.next()));
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
                .min(Comparator.comparing(Vertex::getValue));
        minCell.ifPresent(cell -> this.emptyCell = cell);
    }

    @Override
    public void click(Integer cellValue) {
        this.emptyCell = this.emptyCell.findAdjacentByCellValueAndCallSwap(cellValue);
    }

    @Override
    public void swap(Integer keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        this.emptyCell = this.emptyCell.findAdjacentByKeycodeAndCallSwap(key);
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public Boolean isGameComplete() {
        return IntStream.range(0, this.cells.size())
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.cells.size());

    }
}
