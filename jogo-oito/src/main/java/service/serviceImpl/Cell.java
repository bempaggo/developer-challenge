package service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.enumType.Keyboard;
import service.Edge;
import service.Vertex;

public class Cell implements Vertex {

	private Integer value;
    private final List<Edge> adjacents;
    public static Integer content = 1;

    public Cell(Integer value) {
        this.value = value;
        this.adjacents = new ArrayList<>();
    }

    public Cell() {
        this.value = Cell.content++;
        this.adjacents = new ArrayList<>();
    }

    @Override
    public void creatingHorizontalAdjacent(Vertex cell) {
        adjacents.add(new Adjacent(Keyboard.LEFT, cell));
        cell.getAdjacents().add(new Adjacent(Keyboard.RIGHT, this));
    }

    @Override
    public void creatingVerticalAdjacent(Vertex cell) {
        adjacents.add(new Adjacent(Keyboard.UP, cell));
        cell.getAdjacents().add(new Adjacent(Keyboard.DOWN, this));
    }

    @Override
    public String valueToText() {
        return Optional.of(value)
                .filter(value -> value != 0)
                .map(String::valueOf)
                .orElse("");
    }

    @Override
    public Edge getAdjacentByKeyCode(Keyboard key) {
        Adjacent edge = new Adjacent(key, null);
        Integer indexEdge = adjacents.indexOf(edge);
        return Optional.of(indexEdge)
                .filter(index -> index != -1)
                .map(adjacents::get)
                .orElse(null);
    }

    @Override
    public Vertex click(Keyboard key) {
        Edge adjacent = getAdjacentByKeyCode(key);
        return movement(adjacent);
    }

    private Vertex movement(Edge adjacent) {
        return Optional.ofNullable(adjacent)
                .map(Edge::getCell)
                .map(this::swapCells)
                .orElse(this);
    }

    private Vertex swapCells(Vertex movementCell) {
        setValue(movementCell.getValue());
        movementCell.setValue(0);
        return movementCell;
    }

    @Override
    public Vertex swapCells(Integer value) {
        return adjacents.stream()
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
    public void addAdjacents(Edge edge) {
        this.adjacents.add(edge);
    }
    
    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

	public static Integer getContent() {
		return content;
	}

	public static void setContent(Integer content) {
		Cell.content = content;
	}

}