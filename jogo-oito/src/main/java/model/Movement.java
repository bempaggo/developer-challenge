package model;

import interfaces.Action;
import interfaces.Edge;
import interfaces.Vertex;

import java.util.Optional;

public class Movement implements Action {

    private final Vertex emptyCell;

    private Movement(Vertex emptyCell) {
        this.emptyCell = emptyCell;
    }

    public static Movement of(Vertex emptyCell) {
        return new Movement(emptyCell);
    }

    public Vertex swapCells(Integer value) {
        for (Edge adjacent : emptyCell.getAdjacents()) {
            if (adjacent.cellValueIsEqual(value)) {
                return swapCells(adjacent);
            }
        }
        return emptyCell;
    }

    private Vertex swapCells(Edge adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Edge::getCell)
                .map(this::swapCells)
                .orElse(emptyCell);
    }

    private Vertex swapCells(Vertex movementCell) {
        emptyCell.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

}



