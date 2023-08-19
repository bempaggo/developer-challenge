package util;

import factories.GameFactory;
import interfaces.Graph;
import interfaces.Vertex;
import listeners.BoardUpdateListener;
import model.Keyboard;
import model.Matrix;
import model.MatrixMemento;

import java.util.*;

public class Board implements Graph {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private MatrixMemento gameCompleteBoardPattern;
    private final GameFactory gameFactory;
    private final List<BoardUpdateListener> boardListeners;

    public Board(GameFactory gameFactory) {
        this.boardListeners = new ArrayList<>();
        this.gameFactory = gameFactory;
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public void gameSolutionBoardState() {
        Matrix matrix = gameFactory.createMatrix();
        this.cells = matrix.getComponents();
        this.gameCompleteBoardPattern = new MatrixMemento(List.copyOf(this.getCells()));
        this.defineEmptyCell();
        notifyListeners();
    }

    @Override
    public void gameStartBoardState() {
        Matrix matrix = gameFactory.createMatrix();
        this.cells = matrix.getComponents();
        this.gameCompleteBoardPattern = new MatrixMemento(List.copyOf(this.getCells()));
        this.shuffleCells();
        this.defineEmptyCell();
        notifyListeners();
    }

    private void shuffleCells() {
        Iterator<Vertex> iterator = this.shuffleValues().iterator();
        for (Vertex cell: this.cells) {
            cell.setValue(iterator.next().getValue());
        }
    }

    private List<Vertex> shuffleValues() {
        MatrixMemento shuffledCellValues = new MatrixMemento(this.cells);
        Collections.shuffle(shuffledCellValues.cells());
        return shuffledCellValues.cells();
    }

    private void defineEmptyCell() {
        this.emptyCell = this.cells.stream()
                .min(Comparator.comparing(Vertex::getValue))
                .orElse(null);
    }

    @Override
    public void click(Integer cellValue) {
        this.emptyCell = this.emptyCell.findAdjacentByCellValueAndCallSwap(cellValue);
        notifyListeners();
    }

    @Override
    public void swap(Integer keyCode) {
        this.emptyCell = this.emptyCell.findAdjacentByKeycodeAndCallSwap(Keyboard.fromValue(keyCode));
        notifyListeners();
    }

    @Override
    public Boolean isGameComplete() {
        return this.cells.equals(gameCompleteBoardPattern.cells());
    }

    public void addListener(BoardUpdateListener listener) {
        this.boardListeners.add(listener);
    }

    private void notifyListeners() {
        for (BoardUpdateListener listener : this.boardListeners) {
            listener.updateBoard();
        }
    }

}
