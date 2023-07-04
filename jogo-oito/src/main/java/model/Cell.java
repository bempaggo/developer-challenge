package model;

import java.util.HashMap;
import java.util.Objects;

public class Cell {

    private Integer value;
    private final HashMap<String, Cell> adjacents;

    public Cell(Integer value) {
        this.value = value;
        this.adjacents = new HashMap<>();
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void defineUp(Cell cell) {
        this.adjacents.put("UP", cell);
    }

    public void defineDown(Cell cell) {
        this.adjacents.put("DOWN", cell);
    }

    public void defineLeft(Cell cell) {
        this.adjacents.put("LEFT", cell);
    }

    public void defineRight(Cell cell) {
        this.adjacents.put("RIGHT", cell);

    }

    public String valueToText() {
        return (this.value == 0) ? "" : String.valueOf(this.value);
    }

    public Cell clickDown() {
        return this.movement(this.adjacents.get("UP"));
    }

    public Cell clickUp() {
        return this.movement(this.adjacents.get("DOWN"));
    }

    public Cell clickLeft() {
        return this.movement(this.adjacents.get("RIGHT"));
    }

    public Cell clickRight() {
        return this.movement(this.adjacents.get("LEFT"));
    }

    private Cell movement(Cell movementCell) {
        return movementCell == null ? this : this.swapCells(movementCell);
    }

    private Cell swapCells(Cell movementCell) {
        this.setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    public Cell swapCells(Integer value) {
        return this.adjacents.values().stream()
                .filter(cell -> Objects.equals(cell.value, value))
                .findFirst()
                .map(this::movement)
                .orElse(this);
    }

    public HashMap<String, Cell> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public boolean equals(Object obj) {
        Cell other = (Cell) obj;
        return Objects.equals(this.value, other.value);
    }

}
