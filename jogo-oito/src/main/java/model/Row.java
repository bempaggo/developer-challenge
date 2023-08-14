package model;

import factories.GameFactory;
import factories.GameFactoryImpl;
import interfaces.Vertex;

public final class Row {

    public final Vertex initial;
    public final Vertex center;
    public final Vertex last;
    private final GameFactory gameFactory = new GameFactoryImpl();

    public Row() {
        this.initial = gameFactory.createCell();
        this.center = gameFactory.createCell();
        this.last = gameFactory.createCell();
        this.defineAdjacent();
        this.loadCells();
    }

    public void loadCells() {
        Matrix.cells.add(this.initial);
        Matrix.cells.add(this.center);
        Matrix.cells.add(this.last);
    }

    public void defineAdjacent() {
        this.initial.addAdjacents(gameFactory.createAdjacentLeft(this.center));
        this.center.addAdjacents(gameFactory.createAdjacentRight(this.initial));
        this.center.addAdjacents(gameFactory.createAdjacentLeft(this.last));
        this.last.addAdjacents(gameFactory.createAdjacentRight(this.center));
    }

}