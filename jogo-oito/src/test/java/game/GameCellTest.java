package game;

import interfaces.GameEdge;
import interfaces.GameVertex;
import model.GameAdjacent;
import model.GameCell;
import model.GameKeyboard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameCellTest {

	@Test
	public void testSetValue() {
		GameVertex cell = new GameCell(0);
		cell.setValue(5);
		assertEquals(5, cell.getValue());
	}

	@Test
	public void testGetValue() {
		GameVertex cell = new GameCell(10);
		assertEquals(10, cell.getValue());
	}

	@Test
	public void testCreatingHorizontalAdjacent() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);

		cell1.createHorizontalAdjacent(cell2);

		assertEquals(1, cell1.getAdjacents().size());
		assertEquals(1, cell2.getAdjacents().size());
	}

	@Test
	public void testCreatingVerticalAdjacent() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);

		cell1.createVerticalAdjacent(cell2);

		assertEquals(1, cell1.getAdjacents().size());
		assertEquals(1, cell2.getAdjacents().size());
	}

	@Test
	public void testValueToText() {
		GameVertex cell1 = new GameCell(0);
		GameVertex cell2 = new GameCell(5);

		assertEquals("", cell1.valueToString());
		assertEquals("5", cell2.valueToString());
	}

	@Test
	public void testGetAdjacentByKeyCode() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);
		cell1.createHorizontalAdjacent(cell2);

		assertEquals(cell2, cell1.processCellClick(GameKeyboard.LEFT).getAdjacents());
	}

	@Test
	public void testClick() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);
		cell1.createHorizontalAdjacent(cell2);

		assertEquals(cell2, cell1.processCellClick(GameKeyboard.LEFT));
	}

	@Test
	public void testSwapCells() {
		GameVertex cell1 = new GameCell(0);
		GameVertex cell2 = new GameCell(2);
		cell1.createHorizontalAdjacent(cell2);

		cell1.swapCells(cell2.getValue());

		assertEquals(2, cell1.getValue());
		assertEquals(0, cell2.getValue());
	}

	@Test
	public void testAddAdjacents() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);
		GameEdge edge = new GameAdjacent(GameKeyboard.RIGHT, cell2);

		cell1.addAdjacent(edge);

		List<GameEdge> adjacents = cell1.getAdjacents();
		assertEquals(1, adjacents.size());
		assertEquals(edge, adjacents.get(0));
	}

	@Test
	public void defineAdjacents() {
		GameVertex cell1 = new GameCell(5);
		GameVertex cell2 = new GameCell(10);
		GameVertex cell3 = new GameCell(15);
		GameVertex cell4 = new GameCell(20);

		cell1.createVerticalAdjacent(cell2);
		cell1.createVerticalAdjacent(cell3);
		cell1.createHorizontalAdjacent(cell4);

		List<GameEdge> adjacents = cell1.getAdjacents();
		assertEquals(cell2, adjacents.get(0).getCell());
		assertEquals(cell3, adjacents.get(1).getCell());
		assertEquals(cell4, adjacents.get(2).getCell());
	}
}
