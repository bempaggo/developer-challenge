/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import service.CellImpl;
import service.interfaces.Cell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allen
 */
public final class Matrix {

    private final Row firstRow;
    private final Row secondRow;
    private final Row thirdRow;
    public static List<Cell> cells;


    public Matrix() {
        Matrix.cells = new ArrayList<>();
        CellImpl.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.initial.createVerticalAdjacent(secondRow.initial);
        this.secondRow.initial.createVerticalAdjacent(thirdRow.initial);

        this.firstRow.center.createVerticalAdjacent(secondRow.center);
        this.secondRow.center.createVerticalAdjacent(thirdRow.center);

        this.firstRow.last.createVerticalAdjacent(secondRow.last);
        this.secondRow.last.createVerticalAdjacent(thirdRow.last);
        
        this.changePositionToValidateTemplate();
    }
    
    private void changePositionToValidateTemplate(){
        this.thirdRow.last.setValue(0);
    }
    

    public List<Cell> getCells() {
        return Matrix.cells;
    }

    private static final class Row {

        public final CellImpl initial;
        public final CellImpl center;
        public final CellImpl last;

        public Row() {
            this.initial = new CellImpl();
            this.center = new CellImpl();
            this.last = new CellImpl();
            this.defineAdjacent();
            this.loadCells();
        }

        public void loadCells() {
            Matrix.cells.add(this.initial);
            Matrix.cells.add(this.center);
            Matrix.cells.add(this.last);
        }

        public void defineAdjacent() {
            this.initial.createHorizontalAdjacent(this.center);
            this.center.createHorizontalAdjacent(this.last);
        }

    }
}
