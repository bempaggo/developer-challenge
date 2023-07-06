package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void defineUp(Cell cell) {
        this.adjacents.add(new Edge("UP", cell));
    }

    public void defineDown(Cell cell) {
        this.adjacents.add(new Edge("DOWN", cell));
    }

    public void defineLeft(Cell cell) {
        this.adjacents.add(new Edge("LEFT", cell));
    }

    public void defineRight(Cell cell) {
        this.adjacents.add(new Edge("RIGHT", cell));

    }

    public String valueToText() {
        return (this.value == 0) ? "" : String.valueOf(this.value);
    }

    public Edge getAdjacentBySense(String sense) {
        Edge edge = new Edge(sense, null);
        Integer indexEdge = this.adjacents.indexOf(edge);
        return (indexEdge != -1) ? this.adjacents.get(indexEdge) : null;
    }

    public Cell clickDown() {
        Edge edge = this.getAdjacentBySense("UP");
        return this.movement(edge);
    }

    public Cell clickUp() {
        Edge edge = this.getAdjacentBySense("DOWN");
        return this.movement(edge);
    }

    public Cell clickLeft() {
        Edge edge = this.getAdjacentBySense("RIGHT");
        return this.movement(edge);
    }

    public Cell clickRight() {
        Edge edge = this.getAdjacentBySense("LEFT");
        return this.movement(edge);
    }

    private Cell movement(Edge edge) {
        return edge == null ? this : this.swapCells(edge.getCell());
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
        Cell other = (Cell) obj;
        return Objects.equals(this.value, other.value);
    }

}
