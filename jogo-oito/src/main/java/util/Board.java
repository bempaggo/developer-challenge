package util;

import interfaces.Graph;
import interfaces.Vertex;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Keyboard;
import model.Matrix;

public class Board implements Graph {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private Integer length;
    private Matrix matrix;

    public Board() {
    }

    @Override
    public void showSolvedBoard() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.defineEmptyCell();
    }

    @Override
    public void setUpNewBoard() {
        this.matrix = new Matrix();
        this.cells = this.matrix.getCells();
        this.length = cells.size();
        this.shuffleCells();
        this.defineEmptyCell();
    }

    public Vertex getEmptyCell() {
        return this.emptyCell;
    }

    private void shuffleCells() {
        List<Integer> values = cells.stream()
                .map(Vertex::getValue)
                .collect(Collectors.toList());
        Collections.shuffle(values);
    
        Iterator<Vertex> cellIterator = cells.iterator();
        values.forEach(value -> cellIterator.next().setValue(value));
    }
    
    /* o método buscava a célula de menor valor e retornava ela, se não achasse(ou seja, lista vazia),
    retornava null. Funciona mas não é o ideal. Aqui busca diretamente a célula de valor certo
    e solta exception se não tiver, pois nesse caso não há celula vazia e tem erro na lógica */
    private void defineEmptyCell() {
        this.emptyCell = cells.stream()
                .filter(cell -> cell.getValue().equals(0))
                .findFirst()
                .get();
    }

    @Override
    public void moveCellByValue(Integer cellValue) {
        Vertex targetCell = this.emptyCell.getAdjacentByValue(cellValue);
        this.emptyCell = this.emptyCell.swapCells(targetCell);
    }

    @Override
    public void moveCellByKey(Integer keyCode) {
        Vertex targetCell = this.emptyCell.getAdjacentByKeyCode(Keyboard.fromValue(keyCode));
        this.emptyCell = this.emptyCell.swapCells(targetCell);
    }

    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
    public Boolean checkGameOver() {
        return IntStream.range(0, this.length)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1) % this.length);

    }

}
