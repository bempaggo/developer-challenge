package service;

import service.interfaces.Cell;
import service.interfaces.Edge;
import util.Keyboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CellImpl implements Cell {

    private Integer value;
    private final List<Edge> adjacents;
    public static Integer content;

    public CellImpl(Integer value) {
        this.value = value;
        this.adjacents = new ArrayList<>();
    }

    public CellImpl() {
        this.value = CellImpl.content++;
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
    public void createHorizontalAdjacent(service.interfaces.Cell cell) {
        this.adjacents.add(new EdgeImpl(Keyboard.LEFT, cell));
        cell.getAdjacents().add(new EdgeImpl(Keyboard.RIGHT, this));
    }

    @Override
    public void createVerticalAdjacent(service.interfaces.Cell cell) {
        this.adjacents.add(new EdgeImpl(Keyboard.UP, cell));
        cell.getAdjacents().add(new EdgeImpl(Keyboard.DOWN, this));
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
        EdgeImpl edge = new EdgeImpl(key, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return Optional.of(indexEdge)
                .filter(index -> index != -1)
                .map(this.adjacents::get)
                .orElse(null);
    }

    @Override
    public service.interfaces.Cell click(Keyboard key) {
        Edge adjacent = this.getAdjacentByKeyCode(key);
        return this.movement(adjacent);
    }

    private service.interfaces.Cell movement(Edge adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Edge::getCell)
                .map(this::swapCells)
                .orElse(this);
    }

    private service.interfaces.Cell swapCells(service.interfaces.Cell movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    @Override
    public service.interfaces.Cell swapCells(Integer value) {
        return this.adjacents.stream()
                .filter(adjacent -> Objects.equals(adjacent.getCell().getValue(), value))
                .findFirst()
                .map(this::movement)
                .orElse(this);
    }

    @Override
    public List<Edge> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public void addAdjacent(Edge edge) {
        this.adjacents.add(edge);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((CellImpl) obj).value);
    }

}
