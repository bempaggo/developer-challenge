package util;

import java.util.ArrayList;
import java.util.List;

import interfaces.Vertex;

public record BoardMemento(List<Vertex> cells) {
    public BoardMemento(List<Vertex> cells) {
        // Fazer cópias das células
        this.cells = new ArrayList<>();
        for (Vertex cell : cells) {
            this.cells.add(cell.clone());
        }
    }
}
