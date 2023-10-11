package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import game.enums.Keyboard;
import game.interfaces.Vertex;

public class Cell implements Vertex {

    private Integer value;
    private final List<Adjacent> adjacents;

    public Cell(Integer value) {
        this.value = value;
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
    public void creatingHorizontalAdjacent(Cell cell) {
        this.adjacents.add(new Adjacent(Keyboard.LEFT, cell));
        cell.getAdjacents().add(new Adjacent(Keyboard.RIGHT, this));
    }

    @Override
    public void creatingVerticalAdjacent(Cell cell) {
        this.adjacents.add(new Adjacent(Keyboard.UP, cell));
        cell.getAdjacents().add(new Adjacent(Keyboard.DOWN, this));
    }

    @Override
    public String valueToText() {
        return Optional.of(this.value)
                .filter(value -> value != 0)
                .map(String::valueOf)
                .orElse("");
    }

    @Override
    public Adjacent getAdjacentByKeyCode(Keyboard key) {
        Adjacent edge = new Adjacent(key, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return Optional.of(indexEdge)
                .filter(index -> index != -1)
                .map(this.adjacents::get)
                .orElse(null);
    }

    @Override
    public Cell click(Keyboard key) {
        Adjacent adjacent = this.getAdjacentByKeyCode(key);
        return this.movement(adjacent);
    }

    private Cell movement(Adjacent adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Adjacent::getCell)
                .map(this::swapCells)
                .orElse(this);
    }

    private Cell swapCells(Cell movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    @Override
    public Cell swapCells(Integer value) {
        return this.adjacents.stream()
                .filter(adjacent -> Objects.equals(adjacent.getCell().getValue(), value))
                .findFirst()
                .map(this::movement)
                .orElse(this);
    }

    @Override
    public List<Adjacent> getAdjacents() {
        return this.adjacents;
    }

}
