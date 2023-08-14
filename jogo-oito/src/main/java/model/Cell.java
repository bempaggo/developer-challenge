package model;

import interfaces.Edge;
import interfaces.Vertex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cell implements Vertex {

    private Integer value;
    private final List<Edge> adjacents;
    public static Integer content;

    public Cell() {
        this.value = Cell.content++;
        this.adjacents = new ArrayList<>();
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String valueToText() {
        return Optional.of(this.value)
                .filter(value -> value != 0)
                .map(String::valueOf)
                .orElse("");
    }

    @Override
    public Edge getAdjacentByKeyCode(Keyboard key) {
        Adjacent edge = new Adjacent(key, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return Optional.of(indexEdge)
                .filter(index -> index != -1)
                .map(this.adjacents::get)
                .orElse(null);
    }

    @Override
    public Vertex click(Keyboard key) {
        Edge adjacent = this.getAdjacentByKeyCode(key);
        return this.movement(adjacent);
    }

    private Vertex movement(Edge adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Edge::getCell)
                .map(this::swapCells)
                .orElse(this);
    }

    private Vertex swapCells(Vertex movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    @Override
    public Vertex swapCells(Integer value) {
        return this.adjacents.stream()
                .filter(adjacent -> Objects.equals(adjacent.getCell().getValue(), value))
                .findFirst()
                .map(this::movement)
                .orElse(this);
    }
    @Override
    public void addAdjacents(Edge edge) {
        this.adjacents.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((Cell) obj).value);
    }

}
