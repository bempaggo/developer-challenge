package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.enumType.Keyboard;
import service.Edge;
import service.Vertex;
import service.serviceImpl.Board;
import service.serviceImpl.Cell;

public class BoardTest {

	private Board board;

	@BeforeEach
	public void setUp() {
		board = new Board();
		board.feedback();
	}

	@Test
	@DisplayName("Test swap down")
	public void testSwapDown() {
		board.swap(Keyboard.DOWN.getValue());
		int emptyCellIndex = board.getCells().indexOf(board.getEmptyCell());
		assertEquals(5, emptyCellIndex);
	}

	@Test
	@DisplayName("Test swap up")
	public void testSwapUp() {
		board.swap(Keyboard.UP.getValue());
		int emptyCellIndex = board.getCells().indexOf(board.getEmptyCell());
		assertEquals(8, emptyCellIndex);
	}

	@Test
	@DisplayName("Test get cells")
	public void testGetCells() {
		List<Vertex> cells = board.getCells();
		assertNotNull(cells);
		assertEquals(9, cells.size());
	}

	@Test
	@DisplayName("Test define cell relationships")
	public void testDefineCellRelationships() {
		Vertex cell1 = board.getCells().get(1);
		Vertex cell2 = board.getCells().get(2);

		cell1.creatingHorizontalAdjacent(cell2);

		Edge adjacent1 = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
		Edge adjacent2 = cell2.getAdjacentByKeyCode(Keyboard.RIGHT);
		if (Objects.nonNull(adjacent1) && Objects.nonNull(adjacent2)) {
			assertEquals(cell2, adjacent1.getCell());
			assertEquals(cell1, adjacent2.getCell());
		}
	}

	@Test
	@DisplayName("Test check game over")
	public void testCheckGameOver() {
		assertTrue(board.checkGameOver());
		board = new Board();
		board.setting();
		assertFalse(board.checkGameOver());
	}

	@Test
	@DisplayName("Test value to text")
	public void testValueToText() {
		Vertex cell = new Cell(0);
		assertEquals("", cell.valueToText());

		cell.setValue(5);
		assertEquals("5", cell.valueToText());
	}

	@Test
	@DisplayName("Test get adjacent by key code")
	public void testGetAdjacentByKeyCode() {
		Vertex cell1 = new Cell(1);
		Vertex cell2 = new Cell(2);

		cell1.creatingHorizontalAdjacent(cell2);

		Edge adjacent = cell1.getAdjacentByKeyCode(Keyboard.LEFT);
		if (Objects.nonNull(adjacent)) {
			assertEquals(cell2, adjacent.getCell());
		}

	}
}