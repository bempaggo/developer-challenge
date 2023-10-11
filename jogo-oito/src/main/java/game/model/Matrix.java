/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import game.interfaces.IMatrix;

/**
 *
 * @author allen
 */
public final class Matrix implements IMatrix {

    private List<Cell> cells;
    private Cell emptyCell;
    private final List<Integer> indexHorizontal = Arrays.asList(0,1,3,4,6,7);
    private final List<Integer> indexVertical = Arrays.asList(0,1,2,3,4,5);
    private List<Integer> values;
    private final Integer SIZE = 9;

    public Matrix() {
        this.cells = new ArrayList<>();
        this.values = Arrays.asList(1,2,3,4,5,6,7,8,0);
    }
    
    @Override
    public void createCells(Boolean feedback) {
    	if (!feedback) {
    		Collections.shuffle(values);
    	}
    	
    	IntStream.range(0, SIZE).forEach(index -> this.cells.add(new Cell(this.values.get(index))));
    	this.defineAdjacent();
    	this.defineEmptyCell();
    }
    
    private void defineAdjacent() {
    	IntStream.range(0, SIZE).forEach(index -> {
    		if (indexHorizontal.contains(index))
    			this.cells.get(index).creatingHorizontalAdjacent(this.cells.get(index+1));
    		if (indexVertical.contains(index))
    			this.cells.get(index).creatingVerticalAdjacent(this.cells.get(index+3));
    	});
    }
    
    private void defineEmptyCell() {
        Optional<Cell> minCell = this.cells.stream()
                .min(Comparator.comparing(cell -> cell.getValue()));
        minCell.ifPresent(cell -> {
            this.setEmptyCell(cell);
        });
    }
    
    @Override
    public List<Cell> getCells() {
        return this.cells;
    }

    @Override
	public Cell getEmptyCell() {
		return emptyCell;
	}

    @Override
	public void setEmptyCell(Cell emptyCell) {
		this.emptyCell = emptyCell;
	}

}
