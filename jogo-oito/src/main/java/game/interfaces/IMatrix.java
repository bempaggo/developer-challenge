package game.interfaces;

import java.util.List;

public interface IMatrix {

	public void createCells(Boolean feedback, Integer order);
	
	public List<Vertex> getCells();

	public Vertex getEmptyCell();

	public void setEmptyCell(Vertex emptyCell);
	
}
