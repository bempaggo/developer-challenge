package service.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import model.Matrix;
import model.enumType.Keyboard;
import service.Graph;
import service.Vertex;

public class Board implements Graph {

	private List<Vertex> cells;
	private Vertex emptyCell;
	private Integer length;
	private Matrix matrix;

	public Board() {

	}

	@Override
	public void feedback() {
		matrix = new Matrix();
		cells = matrix.getCells();
		length = cells.size();
		defineEmptyCell();
	}

	@Override
	public void setting() {
		matrix = new Matrix();
		cells = matrix.getCells();
		length = cells.size();
		shuffleCell();
		defineEmptyCell();

	}

	private void shuffleCell() {
		Iterator<Integer> iterator = shuffleValues().iterator();
		cells.stream().forEach(vertex -> vertex.setValue(iterator.next()));
	}

	private List<Integer> shuffleValues() {
		List<Integer> values = new ArrayList<>();
		cells.stream().map(Vertex::getValue).forEach(values::add);
		Collections.shuffle(values);
		return values;
	}

	private void defineEmptyCell() {
		Optional<Vertex> minCell = cells.stream().min(Comparator.comparing(cell -> cell.getValue()));
		minCell.ifPresent(cell -> {
			emptyCell = cell;
		});
	}

	@Override
	public void click(Integer cellValue) {
		emptyCell = emptyCell.swapCells(cellValue);
	}

	@Override
	public void swap(Integer keyCode) {
		Keyboard key = Keyboard.keyboardValue(keyCode);
		emptyCell = emptyCell.click(key);
	}

	@Override
	public Boolean checkGameOver() {
		return IntStream.range(0, length).allMatch(index -> cells.get(index).getValue() == (index + 1) % length);
	}

	public List<Vertex> getCells() {
		return this.cells;
	}

	public Vertex getEmptyCell() {
		return this.emptyCell;
	}

	public Integer getLength() {
		return length;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setCells(List<Vertex> cells) {
		this.cells = cells;
	}

	public void setEmptyCell(Vertex emptyCell) {
		this.emptyCell = emptyCell;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
}
