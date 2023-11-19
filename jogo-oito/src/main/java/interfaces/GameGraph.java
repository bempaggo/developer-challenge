package interfaces;

import java.util.List;

public interface GameGraph {

	void provideFeedback();

	void setupGame();

	void performSwap(Integer keyCode);

	List<GameVertex> getGameCells();

	GameVertex getEmptyCell();

	void processCellClick(Integer cellValue);

	Boolean isGameOver();
}
