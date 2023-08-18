package util;

import factories.GameFactory;
import factories.GameFactoryImpl;
import interfaces.BoardUpdateListener;
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
    private final List<BoardUpdateListener> boardListeners;

    public Board() {
        this.boardListeners = new ArrayList<>();
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
        notifyListeners();
    }

    @Override
    public void gameStartBoardState() {
        Matrix matrix = gameFactory.createMatrix();
        this.cells = matrix.getCells();
        this.gameCompleteBoardPattern = new BoardMemento(List.copyOf(this.getCells()));
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
        BoardMemento shuffledCellValues = new BoardMemento(this.cells);
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
