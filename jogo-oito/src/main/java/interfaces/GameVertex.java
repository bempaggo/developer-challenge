package interfaces;

import java.util.List;

import model.GameKeyboard;

public interface GameVertex {

	void setValue(Integer value);

	Integer getValue();

	void createHorizontalAdjacent(GameVertex cell);

	void createVerticalAdjacent(GameVertex cell);

	String valueToString();

	GameEdge getAdjacentByKeyboard(GameKeyboard keyboard);

	GameVertex processCellClick(GameKeyboard keyboard);

	List<GameEdge> getAdjacents();

	void addAdjacent(GameEdge edge);

	GameVertex swapCells(Integer value);
}
