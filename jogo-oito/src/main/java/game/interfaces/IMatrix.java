package game.interfaces;

import java.util.List;

import game.model.Cell;

public interface IMatrix {

	public void createCells(Boolean feedback);
	
	public List<Cell> getCells();

	public Cell getEmptyCell();

	public void setEmptyCell(Cell emptyCell);
	
}
