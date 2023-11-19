package model;

import interfaces.GameEdge;
import interfaces.GameVertex;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GameCell implements GameVertex {

	private Integer value;
	private final List<GameEdge> adjacents;
	public static Integer content;

	public GameCell(Integer value) {
		this.value = value;
		this.adjacents = new ArrayList<>();
	}

	public GameCell() {
		this.value = GameCell.content++;
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
	public void createHorizontalAdjacent(GameVertex cell) {
		this.adjacents.add(new GameAdjacent(GameKeyboard.LEFT, cell));
		cell.getAdjacents().add(new GameAdjacent(GameKeyboard.RIGHT, this));
	}

	@Override
	public void createVerticalAdjacent(GameVertex cell) {
		this.adjacents.add(new GameAdjacent(GameKeyboard.UP, cell));
		cell.getAdjacents().add(new GameAdjacent(GameKeyboard.DOWN, this));
	}

	@Override
	public String valueToString() {
		return Optional.ofNullable(this.value).filter(value -> value != 0).map(String::valueOf).orElse("");
	}

	@Override
	public GameEdge getAdjacentByKeyboard(GameKeyboard key) {
		return this.adjacents.stream().filter(adjacent -> Objects.equals(adjacent.getKeyboard(), key)).findFirst()
				.orElse(null);
	}

	@Override
	public GameVertex processCellClick(GameKeyboard key) {
		GameEdge adjacent = this.getAdjacentByKeyboard(key);
		return this.movement(adjacent);
	}

	private GameVertex movement(GameEdge adjacent) {
		return Optional.ofNullable(adjacent).map(GameEdge::getCell).map(this::swapCells).orElse(this);
	}

	private GameVertex swapCells(GameVertex movementCell) {
		if (movementCell != null) {
			Integer tempValue = this.value;
			this.setValue(movementCell.getValue());
			movementCell.setValue(tempValue);
		}
		return movementCell;
	}

	@Override
	public GameVertex swapCells(Integer value) {
		return this.adjacents.stream().filter(adjacent -> Objects.equals(adjacent.getCell().getValue(), value))
				.findFirst().map(this::movement).orElse(this);
	}

	@Override
	public List<GameEdge> getAdjacents() {
		return this.adjacents;
	}

	@Override
	public void addAdjacent(GameEdge edge) {
		this.adjacents.add(edge);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		GameCell otherCell = (GameCell) obj;
		return Objects.equals(value, otherCell.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
