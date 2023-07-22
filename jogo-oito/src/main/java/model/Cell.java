package model;

import enums.Keyboard;
import interfaces.Edge;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cell implements Vertex {

    private Integer value;
    private final List<Edge> adjacents;
    protected static Integer content;

    private Cell(Integer value) {
        this.value = value;
        this.adjacents = new ArrayList<>();
    }

    public Cell() {
        this.value = Cell.content++;
        this.adjacents = new ArrayList<>();
    }

    public static Cell of(Integer value) {
        return new Cell(value);
    }

    public static String valueToText(List<Vertex> cells, Integer index) {
        return cells.get(index).valueToText();
    }

    @Override
    public String valueToText() {
        return Optional.of(this.value)
                .filter(value -> value != 0)
                .map(String::valueOf)
                .orElse("");
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
    public void createHorizontalAdjacent(Vertex cell) {
        this.adjacents.add(Adjacent.of(Keyboard.LEFT, cell));
        cell.addAdjacents(Adjacent.of(Keyboard.RIGHT, this));
    }

    @Override
    public void createVerticalAdjacent(Vertex cell) {
        this.adjacents.add(Adjacent.of(Keyboard.UP, cell));
        cell.addAdjacents(Adjacent.of(Keyboard.DOWN, this));
    }

    @Override
    public Edge getAdjacentByKeyCode(Keyboard key) {
        Edge edge = Adjacent.of(key, null);
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
        for (int index = 0; index < this.adjacents.size(); index++) {
            Edge adjacent = this.adjacents.get(index);
            if (adjacent.cellValueIsEqual(value)) {
                return this.movement(adjacent);
            }
        }
        return this;
    }

    @Override
    public List<Edge> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public void addAdjacents(Edge edge) {
        this.adjacents.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((Cell) obj).value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
