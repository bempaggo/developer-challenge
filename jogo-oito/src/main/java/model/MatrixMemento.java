package model;

import interfaces.BoardComponent;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;

public record MatrixMemento(List<Vertex> cells) {

    public MatrixMemento(BoardComponent component) {
        this(component.getComponents());
    }
    public MatrixMemento(List<Vertex> cells) {
        // Fazer cópias das células
        this.cells = new ArrayList<>();
        for (Vertex cell : cells) {
            this.cells.add(cell.clone());
        }
    }

    public boolean equals(BoardComponent component) {
        return this.cells().equals(component.getComponents());
    }

}
