package model;

import interfaces.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public Map<Keyboard, Vertex> getAdjacents() {
        return adjacents;
    }

    @Override
    public Vertex getAdjacentByKeyCode(Keyboard key) {
        return adjacents.getOrDefault(key, this);
    }
    
    @Override
    public Vertex getAdjacentByValue(Integer value) {
        return adjacents.values().stream()
                .filter(adjacent -> adjacent.getValue().equals(value))
                .findFirst()
                .orElse(this);
    }    

    @Override
    public void creatingHorizontalAdjacent(Vertex cell) {
        this.adjacents.put(Keyboard.LEFT, cell);
        cell.getAdjacents().put(Keyboard.RIGHT, this);
    }

    @Override
    public void creatingVerticalAdjacent(Vertex cell) {
        this.adjacents.put(Keyboard.UP, cell);
        cell.getAdjacents().put(Keyboard.DOWN, this);
    }
    
    @Override
    public Vertex swapCells(Vertex movementCell) {
        Integer tempValue = this.getValue();
        this.setValue(movementCell.getValue());
        movementCell.setValue(tempValue);
        return movementCell;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.value, ((Cell) obj).value);
    }

    @Override
    public String valueToText() {
        return String.valueOf(this.value);
    }

}
