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
    private final GameFactory gameFactory = new GameFactoryImpl();

    public Matrix() {
        Matrix.cells = new ArrayList<>();
        Cell.content = 1;
        this.firstRow = gameFactory.createRow();
        this.secondRow = gameFactory.createRow();
        this.thirdRow = gameFactory.createRow();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.initial.addAdjacents(gameFactory.createAdjacentUp(this.secondRow.initial));
        this.secondRow.initial.addAdjacents(gameFactory.createAdjacentDown(this.firstRow.initial));

        this.firstRow.center.addAdjacents(gameFactory.createAdjacentUp(this.secondRow.center));
        this.secondRow.center.addAdjacents(gameFactory.createAdjacentDown(this.firstRow.center));

        this.firstRow.last.addAdjacents(gameFactory.createAdjacentUp(this.secondRow.last));
        this.secondRow.last.addAdjacents(gameFactory.createAdjacentDown(this.firstRow.last));

        this.secondRow.initial.addAdjacents(gameFactory.createAdjacentUp(this.thirdRow.initial));
        this.thirdRow.initial.addAdjacents(gameFactory.createAdjacentDown(this.secondRow.initial));

        this.secondRow.center.addAdjacents(gameFactory.createAdjacentUp(this.thirdRow.center));
        this.thirdRow.center.addAdjacents(gameFactory.createAdjacentDown(this.secondRow.center));

        this.secondRow.last.addAdjacents(gameFactory.createAdjacentUp(this.thirdRow.last));
        this.thirdRow.last.addAdjacents(gameFactory.createAdjacentDown(this.secondRow.last));

        this.changePositionToValidateTemplate();
    }

    private void changePositionToValidateTemplate(){
        this.thirdRow.last.setValue(0);
    }

    public List<Vertex> getCells() {
        return Matrix.cells;
    }
}
