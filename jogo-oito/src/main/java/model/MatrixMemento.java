package model;

import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;

public record MatrixMemento(List<Vertex> cells) {
    public MatrixMemento(List<Vertex> cells) {
        // Fazer cópias das células
        this.cells = new ArrayList<>();
        for (Vertex cell : cells) {
            this.cells.add(cell.clone());
        }
    }
}
