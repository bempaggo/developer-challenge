package model;

import factories.GameFactory;
import factories.GameFactoryImpl;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.List;

public final class Matrix {

    private final Row firstRow;
    private final Row secondRow;
    private final Row thirdRow;
    public static List<Vertex> cells;
    //temp var

    public Matrix() {
        Matrix.cells = new ArrayList<>();
        Cell.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.defineVerticalAdjacency(this.secondRow);
        this.secondRow.defineVerticalAdjacency(this.thirdRow);

        this.changePositionToValidateTemplate();
    }

    private void changePositionToValidateTemplate(){
        this.thirdRow.last.setValue(0);
    }

    public List<Vertex> getCells() {
        return Matrix.cells;
    }

    public static final class Row {

        public final Vertex initial;
        public final Vertex center;
        public final Vertex last;
        private final GameFactory gameFactory = new GameFactoryImpl();

        public Row() {
            this.initial = gameFactory.createCell();
            this.center = gameFactory.createCell();
            this.last = gameFactory.createCell();
            this.defineHorizontalAdjacency();
            this.loadCells();
        }

        public void loadCells() {
            Matrix.cells.add(this.initial);
            Matrix.cells.add(this.center);
            Matrix.cells.add(this.last);
        }

        public void defineVerticalAdjacency(Row row) {
            this.initial.addAdjacents(gameFactory.createAdjacentUp(row.initial));
            this.center.addAdjacents(gameFactory.createAdjacentUp(row.center));
            this.last.addAdjacents(gameFactory.createAdjacentUp(row.last));

            row.initial.addAdjacents(gameFactory.createAdjacentDown(this.initial));
            row.center.addAdjacents(gameFactory.createAdjacentDown(this.center));
            row.last.addAdjacents(gameFactory.createAdjacentDown(this.last));
        }

        public void defineHorizontalAdjacency() {
            this.initial.addAdjacents(gameFactory.createAdjacentLeft(this.center));
            this.center.addAdjacents(gameFactory.createAdjacentRight(this.initial));
            this.center.addAdjacents(gameFactory.createAdjacentLeft(this.last));
            this.last.addAdjacents(gameFactory.createAdjacentRight(this.center));
        }

    }
}
