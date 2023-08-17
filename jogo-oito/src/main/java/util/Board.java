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
