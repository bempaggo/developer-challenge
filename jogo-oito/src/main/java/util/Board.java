package util;

import factories.GameFactory;
import interfaces.Graph;
import interfaces.Vertex;
import listeners.BoardUpdateListener;
import model.Keyboard;
import model.Matrix;
import model.MatrixMemento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Board implements Graph {

    private Matrix matrix;
    private MatrixMemento gameCompleteBoardPattern;
    private final GameFactory gameFactory;
    private final List<BoardUpdateListener> boardListeners;

    public Board(GameFactory gameFactory) {
        this.boardListeners = new ArrayList<>();
        this.gameFactory = gameFactory;
    }

    @Override
    public List<Vertex> getCells() {
        return this.matrix.getComponents();
    }

    @Override
    public void gameSolutionBoardState() {
        this.matrix = gameFactory.createMatrix();
        this.gameCompleteBoardPattern = new MatrixMemento(this.matrix);
        notifyListeners();
    }

    @Override
    public void gameStartBoardState() {
        this.matrix = gameFactory.createMatrix();
        this.gameCompleteBoardPattern = new MatrixMemento(this.matrix);
        this.shuffleCells();
        notifyListeners();
    }

    private void shuffleCells() {
        Iterator<Vertex> iterator = this.shuffleValues().iterator();
        for (Vertex cell : this.matrix.getComponents()) {
            cell.setValue(iterator.next().getValue());
        }
    }

    private List<Vertex> shuffleValues() {
        MatrixMemento shuffledCellValues = new MatrixMemento(this.matrix);
        Collections.shuffle(shuffledCellValues.cells());
        return shuffledCellValues.cells();
    }

    @Override
    public void click(Integer cellValue) {
        this.matrix.performSwap(cellValue);
        notifyListeners();
    }

    @Override
    public void swap(Integer keyCode) {
        this.matrix.performSwap(Keyboard.fromValue(keyCode));
        notifyListeners();
    }

    @Override
    public Boolean isGameComplete() {
        return gameCompleteBoardPattern.equals(this.matrix);
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
