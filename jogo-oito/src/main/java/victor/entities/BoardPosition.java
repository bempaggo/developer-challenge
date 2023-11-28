package victor.entities;

public class BoardPosition {
    private final int xPosition;
    private final int yPosition;

    public BoardPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    @Override
    public String toString() {
        return "BoardPosition { " +
                "xPosition = " + xPosition +
                ", yPosition = " + yPosition +
                " }";
    }
}
