/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.Vertex;

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
    protected static List<Vertex> cells;

    public Matrix() {
        Matrix.cells = new ArrayList<>();
        Cell.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        createVerticalAdjacentInTheInitialCell();
        createVerticalAdjacentInTheCentralCell();
        createVerticalAdjacentInTheLastCell();
        this.changePositionToValidateTemplate();
    }

    private void createVerticalAdjacentInTheLastCell() {
        this.firstRow.last.createVerticalAdjacent(secondRow.last);
        this.secondRow.last.createVerticalAdjacent(thirdRow.last);
    }

    private void createVerticalAdjacentInTheCentralCell() {
        this.firstRow.center.createVerticalAdjacent(secondRow.center);
        this.secondRow.center.createVerticalAdjacent(thirdRow.center);
    }

    private void createVerticalAdjacentInTheInitialCell() {
        this.firstRow.initial.createVerticalAdjacent(secondRow.initial);
        this.secondRow.initial.createVerticalAdjacent(thirdRow.initial);
    }
    
    private void changePositionToValidateTemplate(){
        this.thirdRow.last.setValue(0);
    }
    

    public List<Vertex> getCells() {
        return Matrix.cells;
    }

    private final class Row {

        public final Cell initial;
        public final Cell center;
        public final Cell last;

        public Row() {
            this.initial = new Cell();
            this.center = new Cell();
            this.last = new Cell();
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
