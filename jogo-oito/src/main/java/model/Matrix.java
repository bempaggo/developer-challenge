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
    public static List<Vertex> cells;

    public Matrix() {
        Matrix.cells = new ArrayList<>();
        Cell.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.initial.creatingVerticalAdjacent(secondRow.initial);
        this.secondRow.initial.creatingVerticalAdjacent(thirdRow.initial);

        this.firstRow.center.creatingVerticalAdjacent(secondRow.center);
        this.secondRow.center.creatingVerticalAdjacent(thirdRow.center);

        this.firstRow.last.creatingVerticalAdjacent(secondRow.last);
        this.secondRow.last.creatingVerticalAdjacent(thirdRow.last);

        this.changePositionToValidateTemplate();
    }

    private void changePositionToValidateTemplate() {
        this.thirdRow.last.setValue(0);
    }

    public List<Vertex> getCells() {
        return Matrix.cells;
    }

    private final class Row {

        private final Vertex initial;
        private final Vertex center;
        private final Vertex last;

        private Row() {
            this.initial = new Cell();
            this.center = new Cell();
            this.last = new Cell();
            this.defineAdjacent();
            this.loadCells();
        }

        private void loadCells() {
            Matrix.cells.add(this.initial);
            Matrix.cells.add(this.center);
            Matrix.cells.add(this.last);
        }

        private void defineAdjacent() {
            this.initial.creatingHorizontalAdjacent(this.center);
            this.center.creatingHorizontalAdjacent(this.last);
        }

    }
}
