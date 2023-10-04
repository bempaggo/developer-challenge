package model;

import java.util.ArrayList;
import java.util.List;

import service.Vertex;
import service.serviceImpl.Cell;

public class Matrix {

	private final Row firstRow;
	private final Row secondRow;
	private final Row thirdRow;
	public static List<Vertex> cells;

	public Matrix() {
		Matrix.cells = new ArrayList<>();
		Cell.content = 1;
		this.firstRow = new Row();
		this.secondRow = new Row();
		this.thirdRow = new Row();
		this.defineAdjacent();
	}

	public void defineAdjacent() {
		firstRow.getInitial().creatingVerticalAdjacent(secondRow.getInitial());
		secondRow.getInitial().creatingVerticalAdjacent(thirdRow.getInitial());
		firstRow.getCenter().creatingVerticalAdjacent(secondRow.getCenter());
		secondRow.getCenter().creatingVerticalAdjacent(thirdRow.getCenter());
		firstRow.getLast().creatingVerticalAdjacent(secondRow.getLast());
		secondRow.getLast().creatingVerticalAdjacent(thirdRow.getLast());
		changePositionToValidateTemplate();
	}

	private void changePositionToValidateTemplate() {
		thirdRow.getLast().setValue(0);
	}

	public List<Vertex> getCells() {
		return Matrix.cells;
	}

	public Row getFirstRow() {
		return firstRow;
	}

	public Row getSecondRow() {
		return secondRow;
	}

	public Row getThirdRow() {
		return thirdRow;
	}

	public static void setCells(List<Vertex> cells) {
		Matrix.cells = cells;
	}

}