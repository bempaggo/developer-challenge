package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import interfaces.GameGraph;
import interfaces.GameVertex;
import model.GameKeyboard;
import model.GameMatrix;

public class GameBoard implements GameGraph {

    private List<GameVertex> cells;
    private GameVertex emptyCell;
    private Integer length;
    private GameMatrix matrix;

    public GameBoard() {
    }

    @Override
    public void provideFeedback() {
        this.matrix = new GameMatrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.defineEmptyCell();
    }

    @Override
    public void setupGame() {
        this.matrix = new GameMatrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.shuffleCells();
        this.defineEmptyCell();
    }

    private void shuffleCells() {
        Iterator<Integer> iterator = this.shuffleValues().iterator();
        this.cells.forEach(cell -> cell.setValue(iterator.next()));
    }

    private List<Integer> shuffleValues() {
        List<Integer> values = new ArrayList<>();
        this.cells.stream()
                .map(GameVertex::getValue)
                .forEach(values::add);
        Collections.shuffle(values);
        return values;
    }

    private void defineEmptyCell() {
        Optional<GameVertex> minCell = this.cells.stream()
                .min(Comparator.comparing(GameVertex::getValue));
        minCell.ifPresent(cell -> this.emptyCell = cell);
    }

    @Override
    public void processCellClick(Integer cellValue) {
        this.emptyCell = this.emptyCell.swapCells(cellValue);
    }

    @Override
    public void performSwap(Integer keyCode) {
        GameKeyboard key = GameKeyboard.fromKeyCode(keyCode);
        this.emptyCell = this.emptyCell.processCellClick(key);
    }

    @Override
    public List<GameVertex> getGameCells() {
        return this.cells;
    }

    @Override
    public GameVertex getEmptyCell() {
        return this.emptyCell;
    }

    @Override
    public Boolean isGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);
    }
}
