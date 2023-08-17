package util;

import factories.GameFactory;
import factories.GameFactoryImpl;
import interfaces.Graph;
import interfaces.Vertex;

import java.util.*;

import model.Keyboard;
import model.Matrix;

public class Board implements Graph {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private BoardMemento gameCompleteBoardPattern;
    private final GameFactory gameFactory = new GameFactoryImpl();

    public Board() {
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public void gameSolutionBoardState() {
        Matrix matrix = gameFactory.createMatrix();
        this.cells = matrix.getCells();
        this.gameCompleteBoardPattern = new BoardMemento(List.copyOf(this.getCells()));
        this.defineEmptyCell();
    }

    @Override
    public void gameStartBoardState() {
        Matrix matrix = gameFactory.createMatrix();
        this.cells = matrix.getCells();
        this.gameCompleteBoardPattern = new BoardMemento(List.copyOf(this.getCells()));
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
    // TODO: we can improve this by making it return a cell with value = 0 instead of lambda and comparator
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
    public Boolean isGameComplete() {
        return this.cells.equals(gameCompleteBoardPattern.cells());
    }

}
