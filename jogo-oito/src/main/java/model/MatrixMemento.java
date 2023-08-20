package model;

import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;

public record MatrixMemento(List<Vertex> cells) {

    public MatrixMemento(Matrix matrix) {
        this(matrix.getComponents());
    }
    public MatrixMemento(List<Vertex> cells) {
        // Fazer cópias das células
        this.cells = new ArrayList<>();
        for (Vertex cell : cells) {
            this.cells.add(cell.clone());
        }
    }

    public boolean equals(Matrix matrix) {
        return this.cells().equals(matrix.getComponents());
    }

}
