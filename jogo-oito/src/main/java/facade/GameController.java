package facade;

import java.util.List;

import interfaces.GameGraph;
import interfaces.GameVertex;
import util.GameBoard;

public class GameController {
	private final GameGraph gameBoard;

	public GameController() {
		this.gameBoard = new GameBoard();
	}

	public void provideFeedback() {
		this.gameBoard.provideFeedback();
	}

	public void setupGame() {
		this.gameBoard.setupGame();
	}

	public List<GameVertex> getGameCells() {
		return this.gameBoard.getGameCells();
	}

	public void performSwap(Integer keyCode) {
		this.gameBoard.performSwap(keyCode);
	}

	public Boolean isGameOver() {
		return this.gameBoard.isGameOver();
	}

	public void processCellClick(Integer cellValue) {
		this.gameBoard.processCellClick(cellValue);
	}
}
