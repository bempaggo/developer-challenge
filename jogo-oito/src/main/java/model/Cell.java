package model;

import interfaces.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Cell implements Vertex {

    private Integer value;
    private Map<Keyboard, Vertex> adjacents;
    public static Integer content;

    public Cell(Integer value) {
        this.value = value;
        this.adjacents = new HashMap<>();
    }

    public Cell() {
        this.value = Cell.content++;
        this.adjacents = new HashMap<>();
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
    public void creatingHorizontalAdjacent(Vertex cell) {
        adjacents.put(Keyboard.LEFT, cell);
        cell.getAdjacents().put(Keyboard.RIGHT, this);
    }

    @Override
    public void creatingVerticalAdjacent(Vertex cell) {
        adjacents.put(Keyboard.UP, cell);
        cell.getAdjacents().put(Keyboard.DOWN, this);

    }

    @Override
    public String valueToText() {
        return Optional.ofNullable(value)
                .filter(v -> v != 0)
                .map(String::valueOf)
                .orElse("");
    }

    @Override
    public Vertex click(Keyboard key) {
        return Optional.ofNullable(getAdjacentByKeyCode(key))
                .map(this::swapCells)
                .orElse(this);
    }

    private Vertex getAdjacentByKeyCode(Keyboard key) {
        return adjacents.get(key);
    }

    private Vertex swapCells(Vertex movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    @Override
    public Vertex swapCells(Integer value) {
        return adjacents.values().stream()
                .filter(adjacent -> Objects.equals(adjacent.getValue(), value))
                .findFirst()
                .map(cell -> this.swapCells((Cell) cell))
                .orElse(this);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((Cell) obj).value);
    }

    public Map<Keyboard, Vertex> getAdjacents() {
        return adjacents;
    }

}
