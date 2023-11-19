package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.enumType.Keyboard;
import service.Edge;
import service.Vertex;
import service.serviceImpl.Adjacent;
import service.serviceImpl.Cell;

public class AdjacentTest {

	@Test
	@DisplayName("Test edge creation with right key")
	public void testEdgeCreationWithRightKey() {

		Edge edge = new Adjacent(Keyboard.RIGHT, null);
		Keyboard result = edge.getKey();
		assertEquals(Keyboard.RIGHT, result, "Expected Keyboard.RIGHT");
	}

	@Test
	@DisplayName("Test edge creation with left key")
	public void testEdgeCreationWithLeftKey() {

		Edge edge = new Adjacent(Keyboard.LEFT, null);
		Keyboard result = edge.getKey();
		assertEquals(Keyboard.LEFT, result, "Expected Keyboard.LEFT");
	}

	@Test
	@DisplayName("Test edge cell click right")
	public void testEdgeCellClickRight() {

		Vertex cell = new Cell(1);
		Edge edge = new Adjacent(Keyboard.RIGHT, cell);
		Vertex result = edge.getCell().click(Keyboard.RIGHT);
		assertEquals(cell, result, "Expected the same cell after clicking RIGHT");
	}

	@Test
	@DisplayName("Test edge cell click left")
	public void testEdgeCellClickLeft() {

		Vertex cell = new Cell(2);
		Edge edge = new Adjacent(Keyboard.LEFT, cell);
		Vertex result = edge.getCell().click(Keyboard.LEFT);
		assertEquals(cell, result, "Expected the same cell after clicking LEFT");
	}

}