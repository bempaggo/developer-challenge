package model;

import service.serviceImpl.Cell;

public class Row {
	
	private final Cell initial;
	private final Cell center;
	private final Cell last;

    public Row() {
        this.initial = new Cell();
        this.center = new Cell();
        this.last = new Cell();
        this.defineAdjacent();
        this.loadCells();
    }

    public void loadCells() {
        Matrix.cells.add(initial);
        Matrix.cells.add(center);
        Matrix.cells.add(last);
    }

    public void defineAdjacent() {
        this.initial.creatingHorizontalAdjacent(center);
        this.center.creatingHorizontalAdjacent(last);
    }

	public Cell getInitial() {
		return initial;
	}

	public Cell getCenter() {
		return center;
	}

	public Cell getLast() {
		return last;
	}
}