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

    public List<Edge> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public void addAdjacents(Edge edge) {
        this.adjacents.add(edge);
    }

    @Override
    public String valueToText() {
        return Optional.of(this.value)
                .filter(value -> value != 0)
                .map(String::valueOf)
                .orElse("");
    }

    @Override
    public Vertex findAdjacentByKeycodeAndCallSwap(Keyboard key) {
        Edge adjacent = this.getAdjacentByKeyCode(key);
        return this.findCellInAdjacentsPassingAnEdgeAndCallSwap(adjacent);
    }

    @Override
    public Vertex findAdjacentByCellValueAndCallSwap(Integer value) {
        return this.adjacents.stream()
                .filter(adjacent -> Objects.equals(adjacent.cell().getValue(), value))
                .findFirst()
                .map(this::findCellInAdjacentsPassingAnEdgeAndCallSwap)
                .orElse(this);
    }

    private Vertex findCellInAdjacentsPassingAnEdgeAndCallSwap(Edge adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Edge::cell)
                .map(this::swapCellsValue)
                .orElse(this);
    }

    private Vertex swapCellsValue(Vertex movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    private Edge getAdjacentByKeyCode(Keyboard key) {
        return this.adjacents.stream()
                .filter(adjacent -> adjacent.key() == key)
                .findFirst()
                .orElse(null);
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
