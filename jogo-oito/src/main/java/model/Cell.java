package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {

    private Integer value;
    private final List<Edge> adjacents;

    public Cell(Integer value) {
        this.value = value;
        this.adjacents = new ArrayList<>();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void createAdjacent(Keyboard key, Cell cell) {
        this.adjacents.add(new Edge(key, cell));
    }

    public String valueToText() {
        return (this.value == 0) ? "" : String.valueOf(this.value);
    }

    public Edge getAdjacentByKeyCode(Keyboard key) {
        Edge edge = new Edge(key, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return (indexEdge != -1) ? this.adjacents.get(indexEdge) : null;
    }

    public Cell click(Keyboard key) {
        Edge edge = this.getAdjacentByKeyCode(key);
        return this.movement(edge);
    }


    private Cell movement(Edge edge) {
        return edge == null ? this : this.swapCells(edge.getCell());
    }

    private Cell swapCells(Cell movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    public Cell swapCells(Integer value) {
        return this.adjacents.stream()
                .filter(edge -> Objects.equals(edge.getCell().value, value))
                .findFirst()
                .map(this::movement)
                .orElse(this);
    }

    public List<Edge> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return Objects.equals(this.value, other.value);
    }

}
