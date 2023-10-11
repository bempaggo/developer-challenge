package model;

import interfaces.GameEdge;
import interfaces.GameVertex;
import java.util.Objects;

public class GameAdjacent implements GameEdge {

    private final GameKeyboard keyboard;
    private final GameVertex cell;

    public GameAdjacent(GameKeyboard keyboard, GameVertex cell) {
        this.keyboard = keyboard;
        this.cell = cell;
    }

    @Override
    public GameKeyboard getKeyboard() {
        return this.keyboard;
    }

    @Override
    public GameVertex getCell() {
        return this.cell;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameAdjacent otherAdjacent = (GameAdjacent) obj;
        return Objects.equals(keyboard, otherAdjacent.keyboard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyboard);
    }
}
