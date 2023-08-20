package model;

import interfaces.Edge;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<Edge> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public void addAdjacents(Edge edge) {
        this.adjacents.add(edge);
    }

    @Override
    public String valueToText() {
        return String.valueOf(this.value);
    }

    @Override
    public void performSwap(Integer value) {
        this.adjacents.stream()
                .filter(adjacent -> Objects.equals(adjacent.cell().getValue(), value))
                .findFirst()
                .ifPresent(this::swapValues);
    }

    @Override
    public void performSwap(Keyboard key) {
        this.adjacents.stream()
                .filter(adjacent -> adjacent.key() == key)
                .findFirst()
                .ifPresent(this::swapValues);
    }

    private void swapValues(Edge adjacent) {
        Vertex movementCell = adjacent.cell();
        Integer currentValue = this.getValue();
        Integer newValue = movementCell.getValue();

        this.setValue(newValue);
        movementCell.setValue(currentValue);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((Cell) obj).value);
    }

    @Override
    public Cell clone() {
        try {
            Cell clone = (Cell) super.clone();
            clone.value = this.value;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
