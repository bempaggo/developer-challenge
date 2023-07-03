/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author allen
 */
public final class Cell {

    private final Integer indexRow;
    private final Integer indexColumn;
    private Integer value;
    private boolean validPosition;

    public Cell(Integer indexRow, Integer indexColumn, Integer value) {
        this.indexRow = indexRow;
        this.indexColumn = indexColumn;
        this.value = value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public String valueToText() {
        if (this.value == 0) {
            return "";
        }
        return String.valueOf(this.value);
    }

    public int getIndexRow() {
        return this.indexRow;
    }

    public int getIndexColumn() {
        return this.indexColumn;
    }

    public boolean getValidPosition() {
        return this.validPosition;
    }

    public void setValidPosition(boolean validPosition) {
        this.validPosition = validPosition;
    }

    @Override
    public String toString() {
        return "Cell{" + "indexRow=" + indexRow + ", indexColumn=" + indexColumn + ", value=" + value + ", validPosition=" + validPosition + '}';
    }

}
