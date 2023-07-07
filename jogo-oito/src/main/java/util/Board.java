package util;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Cell;
import model.Keyboard;

public class Board implements Graph {

    final Integer NUM_ROWS = 3;
    final Integer NUM_COLS = 3;
    private List<Vertex> cells;
    private Vertex emptyCell;
    private final Integer length;
    private final Boolean feedback;

    public Board(Boolean feedback) {
        this.feedback = feedback;
        this.length = this.NUM_ROWS * this.NUM_COLS;
        this.emptyCell = null;
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = IntStream.range(1, this.length)
                .boxed()
                .collect(Collectors.toList());
        numbers.add(0);
        return Optional.of(numbers)
                .filter(list -> !this.feedback)
                .map(list -> {
                    Collections.shuffle(list, new Random());
                    return list;
                })
                .orElse(numbers);

    }

    @Override
    public void loadCells() {
        List<Integer> numbers = this.generateRandomNumbers();
        this.cells = numbers.stream()
                .map(number -> new Cell(number))
                .collect(Collectors.toList());
        this.defineEmptyCell();
    }

    private void defineEmptyCell() {
        this.emptyCell = this.cells.get(this.cells.indexOf(new Cell(0)));
    }

    @Override
    public void swap(Integer value) {
        this.emptyCell = this.emptyCell.swapCells(value);
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    private Vertex getCell(Integer index) {
        return this.cells.get(index);
    }

    @Override
    public void defineCellRelationships() {
        this.defineHorizontalRelationship();
        this.defineVerticalRelationship();
    }

    @Override
    public Vertex getEmptyCell() {
        return this.emptyCell;
    }

    private void defineHorizontalRelationship() {
        IntStream.range(1, cells.size() - 1)
                .filter(i -> i % 3 == 1)
                .forEach(i -> {
                    Vertex previous = this.getCell(i - 1);
                    Vertex next = this.getCell(i + 1);
                    Vertex current = this.getCell(i);
                    next.creatingHorizontalAdjacent(current);
                    current.creatingHorizontalAdjacent(previous);
                });
    }

    private void defineVerticalRelationship() {
        IntStream.range(3, 6)
                .forEach(i -> {
                    Vertex previous = this.getCell(i - 3);
                    Vertex next = this.getCell(i + 3);
                    Vertex current = this.getCell(i);
                    next.creatingVerticalAdjacent(current);
                    current.creatingVerticalAdjacent(previous);
                });
    }

    @Override
    public void click(Integer keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        this.emptyCell = this.emptyCell.click(key);
    }

    @Override
    public Boolean checkGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);

    }
}
