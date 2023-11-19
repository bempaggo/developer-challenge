package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.GameEdge;
import interfaces.GameVertex;
import model.GameCell;
import model.GameKeyboard;
import util.GameBoard;

public class GameBoardTest {

	private GameBoard board;

	@BeforeEach
	public void setUp() {
		board = new GameBoard();
		board.provideFeedback();
	}

	@Test
	public void testSwap() {
		GameVertex emptyCell = board.getEmptyCell();
		GameVertex cell = board.getGameCells().get(7);
		Integer cellValue = cell.getValue();

		board.processCellClick(cellValue);

		assertEquals(cellValue, emptyCell.getValue());
		assertEquals(0, cell.getValue());
	}

	@Test
	public void testGetCells() {
		List<GameVertex> cells = board.getGameCells();

		assertNotNull(cells);
		assertEquals(9, cells.size());
	}

	@Test
	public void testDefineCellRelationships() {
		GameVertex cell1 = board.getGameCells().get(1);
		GameVertex cell2 = board.getGameCells().get(2);

		GameEdge adjacent1 = cell1.getAdjacentByKeyboard(GameKeyboard.LEFT);
		GameEdge adjacent2 = cell2.getAdjacentByKeyboard(GameKeyboard.RIGHT);

		assertEquals(cell2, adjacent1.getCell());
		assertEquals(cell1, adjacent2.getCell());
	}

	@Test
	public void testClick() {
		GameVertex emptyCell = board.getEmptyCell();
		GameVertex cell = board.getGameCells().get(7);
		Integer cellValue = cell.getValue();

		board.processCellClick(cellValue);

		assertEquals(cellValue, emptyCell.getValue());
		assertEquals(0, cell.getValue());
	}

	@Test
	public void testCheckGameOver() {
		Assertions.assertTrue(board.isGameOver());
		board = new GameBoard();
		board.setupGame();
		Assertions.assertFalse(board.isGameOver());

	}

	@Test
	public void testCellCreatingHorizontalAdjacent() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);

		cell1.createHorizontalAdjacent(cell2);

		GameEdge adjacent1 = cell1.getAdjacentByKeyboard(GameKeyboard.LEFT);
		GameEdge adjacent2 = cell2.getAdjacentByKeyboard(GameKeyboard.RIGHT);

		assertEquals(cell2, adjacent1.getCell());
		assertEquals(cell1, adjacent2.getCell());
	}

	@Test
	public void testCellCreatingVerticalAdjacent() {
	    GameVertex cell1 = new GameCell(1);
	    GameVertex cell2 = new GameCell(2);

	    cell1.createVerticalAdjacent(cell2);

	    GameEdge adjacent1 = cell1.getAdjacentByKeyboard(GameKeyboard.UP);
	    GameEdge adjacent2 = cell2.getAdjacentByKeyboard(GameKeyboard.DOWN);

	    assertEquals(cell2, adjacent1.getCell());
	    assertEquals(cell1, adjacent2.getCell());
	}


	@Test
	public void testValueToText() {
		GameVertex cell = new GameCell(0);
		assertEquals("", cell.valueToString());

		cell.setValue(5);
		assertEquals("5", cell.valueToString());
	}

	@Test
	public void testGetAdjacentByKeyCode() {
		GameVertex cell1 = new GameCell(1);
		GameVertex cell2 = new GameCell(2);

		cell1.createHorizontalAdjacent(cell2);

		GameEdge adjacent = cell1.getAdjacentByKeyboard(GameKeyboard.LEFT);
		assertEquals(cell2, adjacent.getCell());
	}

	@Test
	public void testClickDown() {
		board.performSwap(GameKeyboard.DOWN.getKeyCode());
		Assertions.assertEquals(5, board.getGameCells().indexOf(board.getEmptyCell()));
	}

	@Test
	public void testClickUp() {
		board.performSwap(GameKeyboard.UP.getKeyCode());
		Assertions.assertEquals(8, board.getGameCells().indexOf(board.getEmptyCell()));
	}

	@Test
	public void testClickRight() {
		board.performSwap(GameKeyboard.RIGHT.getKeyCode());
		Assertions.assertEquals(7, board.getGameCells().indexOf(board.getEmptyCell()));
	}

	@Test
	public void testClickLeft() {
		board.performSwap(GameKeyboard.LEFT.getKeyCode());
		Assertions.assertEquals(8, board.getGameCells().indexOf(board.getEmptyCell()));
	}
}
