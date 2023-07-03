package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author allen
 */
public class Board {

    private final Integer length;
    private final Integer rowSize;
    private final Integer columnSize;
    private final Integer seed;
    private List<List<Cell>> matrix;

    public Board(Integer rowSize, Integer columnSize, Integer seed) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.length = rowSize * columnSize;
        this.seed = seed;
    }
    
    private List<Integer> generateListWithScrambledNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(1, this.length - 1)
                .boxed()
                .collect(Collectors.toList());
        numbers.add(0);
        if (this.seed > 0) {
            Collections.shuffle(numbers, new Random(seed));
        } else if (this.seed == 0) {
            Collections.shuffle(numbers);
        } 
        return numbers;

    }

    public List<List<Cell>> getMatrix() {
        return this.matrix;
    }

    public Integer getLength() {
        return this.length;
    }

    public Integer getRowSize() {
        return this.rowSize;
    }

    public Integer getColumnSize() {
        return this.columnSize;
    }

    public void fillMatrix() {
        this.matrix = new ArrayList<>();
        List<Integer> numbers = this.generateListWithScrambledNumbers();
        for (int indexRow = 0; indexRow < this.rowSize; indexRow += 1) {
            List<Cell> rows = new ArrayList<>();
            for (int indexColumn = 0; indexColumn < this.columnSize; indexColumn += 1) {
                Cell cell = new Cell(indexRow, indexColumn, numbers.remove(0));
                rows.add(cell);
            }
            matrix.add(rows);
        }
        this.validMovePositions();
    }

    public void print() {
        for (List<Cell> rows : this.matrix) {
            for (Cell cell : rows) {
                System.out.println(cell.getValue());
            }
        }

    }

    public Cell findEmptyCell() {
        return this.findCellByValue(0);
    }

    public Cell findCellByValue(Integer value) {
        for (int indexRow = 0; indexRow < this.rowSize; indexRow += 1) {
            for (int indexColumn = 0; indexColumn < this.columnSize; indexColumn += 1) {
                Cell cell = this.matrix.get(indexRow).get(indexColumn);
                if (Objects.equals(cell.getValue(), value)) {
                    return cell;
                }
            }
        }
        return null;
    }

    public boolean positionIsValid(int newIndexRow, int newIndexColumn) {
        return (newIndexRow >= 0
                && newIndexRow < this.rowSize
                && newIndexColumn >= 0
                && newIndexColumn < this.columnSize);

    }

    public Cell getCellByRowAndColumnIndex(int indexRow, int indexColumn) {
        return this.matrix.get(indexRow).get(indexColumn);

    }

    public void swapValue(Cell emptyCell, Cell exchangeCell) {
        emptyCell.setValue(exchangeCell.getValue());
        exchangeCell.setValue(0);
        this.validMovePositions();
    }

    public boolean checkGameOver() {
        int count = 1;
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                Cell current = this.getCellByRowAndColumnIndex(i, j);
                if (current.getValue() != count % this.length) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    private void invalidatePosition() {
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.columnSize; j++) {
                Cell current = this.getCellByRowAndColumnIndex(i, j);
                current.setValidPosition(false);
            }
        }

    }

    public void validMovePositions() {
        this.invalidatePosition();
        Cell emptyCell = this.findEmptyCell();
        int[][] pares = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] par : pares) {
            int indexRow = emptyCell.getIndexRow() + par[0];
            int indexColumn = emptyCell.getIndexColumn() + par[1];
            if (this.positionIsValid(indexRow, indexColumn)) {
                Cell cell = this.getCellByRowAndColumnIndex(indexRow, indexColumn);
                cell.setValidPosition(true);
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board(3, 3, -1);
        board.fillMatrix();
        board.validMovePositions();
    }

}
