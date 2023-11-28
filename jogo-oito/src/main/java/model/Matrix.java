/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.DefinableAdjacent;
import interfaces.Vertex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allen
 */
public final class Matrix implements DefinableAdjacent{

    private List<Row> rows;
    private final int NUMBER_OF_ROWS = 3;
    public static List<Vertex> cells;

    public Matrix() {
        Matrix.cells = new ArrayList<>();
        Cell.content = 1;
        this.rows = new ArrayList<>();
        for(int i =0; i < NUMBER_OF_ROWS; i++){
            this.rows.add(new Row());
        }
        this.defineAdjacent();
    }

    public void defineAdjacent() {
        for(int i = 0; i < NUMBER_OF_ROWS - 1; i++){
            Row upRow = rows.get(i);
            Row downRow = rows.get(i + 1);
            for(int j = 0; j < upRow.cells.size();j++){
                Vertex upCell = upRow.cells.get(j);
                Vertex downCell = downRow.cells.get(j);
                upCell.creatingVerticalAdjacent(downCell);
            }
        }
        this.changePositionToValidateTemplate();
    }
    
    private void changePositionToValidateTemplate(){
        this.rows.get(NUMBER_OF_ROWS - 1).cells.get(2).setValue(0);
    }
    

    public List<Vertex> getCells() {
        return Matrix.cells;
    }

    public List<Row> getRows() {
        return this.rows;
    }

    private final class Row implements DefinableAdjacent{

        private List<Vertex> cells;
        private final int NUMBER_OF_CELLS = 3;
        public Row() {
            this.cells = new ArrayList<>();
            for(int i = 0; i < NUMBER_OF_CELLS; i++){
                this.cells.add(new Cell());
            }
            this.defineAdjacent();
            this.loadCells();
        }

        public void loadCells() {
            Matrix.cells.addAll(this.cells);
        }

        public void defineAdjacent() {
            for(int i = 0; i < NUMBER_OF_CELLS - 1; i++){
                Vertex leftCell = this.cells.get(i);
                Vertex rightCell = this.cells.get(i + 1);
                leftCell.creatingHorizontalAdjacent(rightCell);
            }
        }
    }
}
