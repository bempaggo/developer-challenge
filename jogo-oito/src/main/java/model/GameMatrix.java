package model;

import interfaces.GameVertex;
import java.util.ArrayList;
import java.util.List;

public final class GameMatrix {

    private final GameRow firstRow;
    private final GameRow secondRow;
    private final GameRow thirdRow;
    public static List<GameVertex> cells;

    public GameMatrix() {
        GameMatrix.cells = new ArrayList<>();
        GameCell.content = 1;
        this.firstRow = new GameRow();
        this.secondRow = new GameRow();
        this.thirdRow = new GameRow();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.initial.createVerticalAdjacent(this.secondRow.initial);
        this.secondRow.initial.createVerticalAdjacent(this.thirdRow.initial);

        this.firstRow.center.createVerticalAdjacent(this.secondRow.center);
        this.secondRow.center.createVerticalAdjacent(this.thirdRow.center);

        this.firstRow.last.createVerticalAdjacent(this.secondRow.last);
        this.secondRow.last.createVerticalAdjacent(this.thirdRow.last);

        this.changePositionToValidateTemplate();
    }

    private void changePositionToValidateTemplate() {
        this.thirdRow.last.setValue(0);
    }

    public List<GameVertex> getCells() {
        return GameMatrix.cells;
    }

    private final class GameRow {

        public final GameCell initial;
        public final GameCell center;
        public final GameCell last;

        public GameRow() {
            this.initial = new GameCell();
            this.center = new GameCell();
            this.last = new GameCell();
            this.defineAdjacent();
            this.loadCells();
        }

        public void loadCells() {
            GameMatrix.cells.add(this.initial);
            GameMatrix.cells.add(this.center);
            GameMatrix.cells.add(this.last);
        }

        public void defineAdjacent() {
            this.initial.createHorizontalAdjacent(this.center);
            this.center.createHorizontalAdjacent(this.last);
        }
    }
}
