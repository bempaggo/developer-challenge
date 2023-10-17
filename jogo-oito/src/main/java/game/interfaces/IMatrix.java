package game.interfaces;

import java.util.List;

public interface IMatrix {

	public void createCells(Boolean feedback);
	
	public List<Vertex> getCells();

	public void click(Integer cellValue);

	public void swap(Integer keyCode);

	public Vertex getEmptyCell();

	public Boolean checkVictory();
	
}
