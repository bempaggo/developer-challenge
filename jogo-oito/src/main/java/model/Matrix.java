package model;

import interfaces.Vertex;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a matriz do jogo.
 *
 * @author quintino
 */
public final class Matrix {
    private final Row firstRow;
    private final Row secondRow;
    private final Row thirdRow;
    public static List<Vertex> cells = new ArrayList<>();

    public Matrix() {
        Cell.content = 1;
        this.firstRow = new Row();
        this.secondRow = new Row();
        this.thirdRow = new Row();
        this.defineAdjacent();
    }

    private void defineAdjacent() {
        this.firstRow.initial.creatingVerticalAdjacent(secondRow.initial);
        this.secondRow.initial.creatingVerticalAdjacent(thirdRow.initial);
        this.firstRow.center.creatingVerticalAdjacent(secondRow.center);
        this.secondRow.center.creatingVerticalAdjacent(thirdRow.center);
        this.firstRow.last.creatingVerticalAdjacent(secondRow.last);
        this.secondRow.last.creatingVerticalAdjacent(thirdRow.last);
        this.changePositionToValidateTemplate();
    }

    private void changePositionToValidateTemplate(){
        this.thirdRow.last.setValue(0);
    }


    public List<Vertex> getCells() {
        return Matrix.cells;
    }

}
