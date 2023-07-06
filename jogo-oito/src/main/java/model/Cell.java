package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return Optional.of(this.value)
                .filter(v -> v != 0)
                .map(String::valueOf)
                .orElse("");
    }

    public Edge getAdjacentByKeyCode(Keyboard key) {
        Edge edge = new Edge(key, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return Optional.of(indexEdge)
                .filter(index -> index != -1)
                .map(this.adjacents::get)
                .orElse(null);
    }

    public Cell click(Keyboard key) {
        Edge edge = this.getAdjacentByKeyCode(key);
        return this.movement(edge);
    }

    private Cell movement(Edge edge) {
        return Optional.ofNullable(edge)
                .map(Edge::getCell)
                .map(this::swapCells)
                .orElse(this);
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
        return Objects.equals(this.value, ((Cell) obj).value);
    }

}
