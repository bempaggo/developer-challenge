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
import model.BoardDirectionValidator;
import model.Keyboard;

public class Board implements Graph{

    final Integer NUM_ROWS = 3;
    final Integer NUM_COLS = 3;
    private List<Vertex> cells;
    private Vertex emptyCell;
    private final Integer length;
    private final Boolean feedback;

    private final BoardDirectionValidator direction;

    public Board(Boolean feedback) {
        this.direction = new BoardDirectionValidator(NUM_ROWS, NUM_COLS);
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

    private Integer getCellIndex(Integer row, Integer col) {
        return row * NUM_COLS + col;
    }

    private Vertex getCell(Integer index) {
        return this.cells.get(index);
    }

    @Override
    public void defineCellRelationships() {
        List<CellRelationshipFunction> relationshipMethods = this.defineFunctionsRelationship();
        IntStream.range(0, this.NUM_ROWS)
                .forEach(row -> IntStream.range(0, this.NUM_COLS)
                .forEach(col -> {
                    Integer currentCellIndex = getCellIndex(row, col);
                    Vertex currentCell = getCell(currentCellIndex);
                    relationshipMethods.stream()
                            .filter(method -> this.direction.isValidDirection(row, col, relationshipMethods.indexOf(method)))
                            .forEach(method -> method.apply(currentCell, currentCellIndex));
                }));
    }

    @Override
    public Vertex getEmptyCell() {
        return this.emptyCell;
    }

    interface CellRelationshipFunction {

        void apply(Vertex currentCell, Integer currentCellIndex);
    }

    private List<CellRelationshipFunction> defineFunctionsRelationship() {
        List<CellRelationshipFunction> relationshipMethods = new ArrayList<>();
        relationshipMethods.add(this::defineLeftRelationship);
        relationshipMethods.add(this::defineRightRelationship);
        relationshipMethods.add(this::defineUpRelationship);
        relationshipMethods.add(this::defineDownRelationship);
        return relationshipMethods;
    }

    private void defineLeftRelationship(Vertex currentCell, Integer currentCellIndex) {
        Vertex cell = getCell(currentCellIndex - 1);
        currentCell.createAdjacent(Keyboard.RIGHT, cell);
        cell.createAdjacent(Keyboard.LEFT, currentCell);
    }

    private void defineRightRelationship(Vertex currentCell, Integer currentCellIndex) {
        Vertex cell = getCell(currentCellIndex + 1);
        currentCell.createAdjacent(Keyboard.LEFT, cell);
        cell.createAdjacent(Keyboard.RIGHT, currentCell);
    }

    private void defineUpRelationship(Vertex currentCell, Integer currentCellIndex) {
        Vertex cell = getCell(currentCellIndex - this.NUM_COLS);
        currentCell.createAdjacent(Keyboard.DOWN, cell);
        cell.createAdjacent(Keyboard.UP, currentCell);
    }

    private void defineDownRelationship(Vertex currentCell, Integer currentCellIndex) {
        Vertex cell = getCell(currentCellIndex + this.NUM_COLS);
        currentCell.createAdjacent(Keyboard.UP, cell);
        cell.createAdjacent(Keyboard.DOWN, currentCell);
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
