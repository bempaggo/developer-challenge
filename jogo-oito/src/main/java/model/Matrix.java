package model;

import factories.GameFactory;
import interfaces.Component;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;

public final class Matrix implements Component {

    private Row firstRow;
    private Row secondRow;
    private Row thirdRow;
    private List<Vertex> cells;
    private final GameFactory gameFactory;

    public Matrix(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        this.populateMatrix();
        this.defineAdjacent();
        this.changePositionToValidateTemplate();
    }

    private void populateMatrix() {
        this.cells = new ArrayList<>();
        Cell.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
    }

    private void defineAdjacent() {
        this.firstRow.defineVerticalAdjacency(this.secondRow);
        this.secondRow.defineVerticalAdjacency(this.thirdRow);
    }

    private void changePositionToValidateTemplate() {
        this.thirdRow.last.setValue(0);
    }

    public List<Vertex> getComponents() {
        return this.cells;
    }

    public Vertex getComponent(Integer cellValue) {
        Vertex tempCell = gameFactory.createCell();
        tempCell.setValue(cellValue);
        return this.cells.get(this.cells.indexOf(tempCell));
    }

    public void performSwap(Integer cellValue) {
        this.getComponent(0).performSwap(cellValue);
    }

    public void performSwap(Keyboard key) {
        this.getComponent(0).performSwap(key);
    }

    public final class Row {

        private final Vertex initial;
        private final Vertex center;
        private final Vertex last;

        public Row() {
            this.initial = gameFactory.createCell();
            this.center = gameFactory.createCell();
            this.last = gameFactory.createCell();
            this.defineHorizontalAdjacency();
            this.populateRow();
        }

        private void populateRow() {
            cells.add(this.initial);
            cells.add(this.center);
            cells.add(this.last);
        }

        private void defineVerticalAdjacency(Row row) {
            this.initial.addAdjacents(gameFactory.createAdjacentUp(row.initial));
            this.center.addAdjacents(gameFactory.createAdjacentUp(row.center));
            this.last.addAdjacents(gameFactory.createAdjacentUp(row.last));

            row.initial.addAdjacents(gameFactory.createAdjacentDown(this.initial));
            row.center.addAdjacents(gameFactory.createAdjacentDown(this.center));
            row.last.addAdjacents(gameFactory.createAdjacentDown(this.last));
        }

        private void defineHorizontalAdjacency() {
            this.initial.addAdjacents(gameFactory.createAdjacentLeft(this.center));
            this.center.addAdjacents(gameFactory.createAdjacentRight(this.initial));
            this.center.addAdjacents(gameFactory.createAdjacentLeft(this.last));
            this.last.addAdjacents(gameFactory.createAdjacentRight(this.center));
        }

    }
}
