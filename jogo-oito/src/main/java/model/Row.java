package model;

/**
 * Classe que representa a linha da matriz do jogo.
 *
 * @author quintino
 */
public class Row {
    public final Cell initial = new Cell();
    public final Cell center = new Cell();
    public final Cell last = new Cell();

    public Row() {
        this.defineAdjacent();
        this.loadCells();
    }

    public void loadCells() {
        Matrix.cells.add(this.initial);
        Matrix.cells.add(this.center);
        Matrix.cells.add(this.last);
    }

    public void defineAdjacent() {
        this.initial.creatingHorizontalAdjacent(this.center);
        this.center.creatingHorizontalAdjacent(this.last);
    }
}
