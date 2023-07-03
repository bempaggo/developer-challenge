package facade;

import java.util.List;
import model.Board;
import model.Cell;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author allen
 */
public class Controller {

    private final Board board;

    public Controller(Integer maxRows, Integer maxColumns, Integer seed) {
        this.board = new Board(maxRows, maxColumns, seed);
    }

    public void configBoard() {
        this.board.fillMatrix();
    }

    public List<List<Cell>> getMatrix() {
        return this.board.getMatrix();
    }

    public Cell findCellByValue(Integer value) {
        return this.board.findCellByValue(value);
    }

    public void swapValue(Cell emptyCell, Cell exchangeCell) {
        this.board.swapValue(emptyCell, exchangeCell);
    }

    public Cell findEmptyCell() {
        return this.board.findEmptyCell();
    }
    
    
    public void print(){
        this.board.print();
    }

    public List<List<Cell>> swapCells(int rowMovement, int columnMovement) {
        Cell emptyCell = this.findEmptyCell();
        int newIndexRow = emptyCell.getIndexRow() + rowMovement;
        int newIndexColumn = emptyCell.getIndexColumn() + columnMovement;
        if (!this.board.positionIsValid(newIndexRow, newIndexColumn)) {
            return this.getMatrix();
        }
        Cell exchangeCell = this.board.getCellByRowAndColumnIndex(newIndexRow, newIndexColumn);
        this.swapValue(emptyCell, exchangeCell);
        return this.getMatrix();
    }

    public boolean checkGameOver() {
        return this.board.checkGameOver();
    }

}
