package interfaces;

import model.GameKeyboard;

public interface GameEdge {
	GameKeyboard getKeyboard();

	GameVertex getCell();
}
