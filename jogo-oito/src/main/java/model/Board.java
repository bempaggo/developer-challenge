package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    final Integer NUM_ROWS = 3;
    final Integer NUM_COLS = 3;

    private List<Cell> cells;
    private Cell emptyCell;
    private final Integer length;
    private final Integer seed;
    
    private final Direction direction;

    public Board(Integer seed) {
        this.direction = new Direction(NUM_ROWS, NUM_COLS);
        this.seed = seed;
        this.length = this.NUM_ROWS * this.NUM_COLS;
        this.emptyCell = null;
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = IntStream.range(1, this.length)
                .boxed()
                .collect(Collectors.toList());
        numbers.add(0);
        if (this.seed > 0) Collections.shuffle(numbers, new Random(seed));
        else if (this.seed == 0) Collections.shuffle(numbers);
        return numbers;
    }

    public void loadCells() {
        List<Integer> numbers = this.generateRandomNumbers();
        this.cells = new ArrayList<>();
        for (Integer number : numbers) {
            Cell cell = new Cell(number);
            this.cells.add(cell);
        }
        this.defineEmptyCell();
    }

    private void defineEmptyCell() {
        this.emptyCell = this.emptyCell = this.cells.get(this.cells.indexOf(new Cell(0)));
    }

    public void swap(Integer value) {
        this.emptyCell = this.emptyCell.swapCells(value);
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    private int getCellIndex(int row, int col) {
        return row * NUM_COLS + col;
    }

    private Cell getCell(int index) {
        return this.cells.get(index);
    }

    public void defineCellRelationships() {
        List<CellRelationshipFunction> relationshipMethods = this.defineFunctionsRelationship();
        IntStream.range(0, this.NUM_ROWS)
                .forEach(row -> IntStream.range(0, this.NUM_COLS)
                .forEach(col -> {
                    int currentCellIndex = getCellIndex(row, col);
                    Cell currentCell = getCell(currentCellIndex);
                    relationshipMethods.stream()
                            .filter(method -> this.direction.isValidDirection(row, col, relationshipMethods.indexOf(method)))
                            .forEach(method -> method.apply(currentCell, currentCellIndex));

                }));
    }

    public Cell getEmptyCell() {
        return this.emptyCell;
    }

    interface CellRelationshipFunction {

        void apply(Cell currentCell, int currentCellIndex);
    }

    private List<CellRelationshipFunction> defineFunctionsRelationship() {
        List<CellRelationshipFunction> relationshipMethods = new ArrayList<>();
        relationshipMethods.add(this::defineLeftRelationship);
        relationshipMethods.add(this::defineRightRelationship);
        relationshipMethods.add(this::defineUpRelationship);
        relationshipMethods.add(this::defineDownRelationship);
        return relationshipMethods;
    }

    private void defineLeftRelationship(Cell currentCell, int currentCellIndex) {
        Cell leftCell = getCell(currentCellIndex - 1);
        currentCell.defineLeft(leftCell);
        leftCell.defineRight(currentCell);
    }

    private void defineRightRelationship(Cell currentCell, int currentCellIndex) {
        Cell rightCell = getCell(currentCellIndex + 1);
        currentCell.defineRight(rightCell);
        rightCell.defineLeft(currentCell);
    }

    private void defineUpRelationship(Cell currentCell, int currentCellIndex) {
        Cell upCell = getCell(currentCellIndex - this.NUM_COLS);
        currentCell.defineUp(upCell);
        upCell.defineDown(currentCell);
    }

    private void defineDownRelationship(Cell currentCell, int currentCellIndex) {
        Cell downCell = getCell(currentCellIndex + this.NUM_COLS);
        currentCell.defineDown(downCell);
        downCell.defineUp(currentCell);
    }

    public void clickUp() {
        this.emptyCell = this.emptyCell.clickUp();
    }

    public void clickDown() {
        this.emptyCell = this.emptyCell.clickDown();
    }

    public void clickLeft() {
        this.emptyCell = this.emptyCell.clickLeft();
    }

    public void clickRight() {
        this.emptyCell = this.emptyCell.clickRight();
    }

    public boolean checkGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(i -> this.cells.get(i).getValue() == (i + 1) % this.length);

    }
}
