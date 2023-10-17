/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import game.enums.Keyboard;
import game.interfaces.IMatrix;
import game.interfaces.Vertex;

/**
 *
 * @author allen
 */
public final class Matrix implements IMatrix {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private final List<Integer> values;
    private final Integer SIZE = 9;
    private final Integer ORDER = 3;

    public Matrix() {
        this.cells = new ArrayList<>();
        this.values = this.createValues();
        this.defineAdjacent();
    }
    
    @Override
    public void createCells(Boolean feedback) {	
    	if (!feedback) {
    		Collections.shuffle(this.values);
    	} else {
    		Collections.sort(this.values);
    	}
    	
    	IntStream.range(0, this.SIZE).forEach(index -> {
    		this.cells.get(index).setValue(this.values.get(index));
    		if (this.values.get(index).equals(9))
    			this.emptyCell = this.cells.get(index);
    	});
    }
    
    private List<Integer> createValues() {
    	List<Integer> numbers = new ArrayList<>();
    	
    	IntStream.range(0, this.SIZE).forEach(index -> {
    		numbers.add(index+1);
    		this.cells.add(new Cell());
    	});
    	
    	return numbers;
    }
    
    private void defineAdjacent() {
    	IntStream.range(0, this.ORDER).forEach(row -> {
    		IntStream.range(0, this.ORDER).forEach(col -> {
    			Integer index = this.getIndexCell(row, col);
    			if (col < this.ORDER-1)
    				this.cells.get(index).creatingHorizontalAdjacent(this.cells.get(index+1));
    			if (row < this.ORDER-1)
    				this.cells.get(index).creatingVerticalAdjacent(this.cells.get(index+this.ORDER));
    		});
    	});
    }
    
    private Integer getIndexCell(Integer row, Integer col) {
    	return row * this.ORDER + col;
    }
    
    @Override
    public void click(Integer cellValue) {
        this.emptyCell = this.emptyCell.swapCells(cellValue);
    }

    @Override
    public void swap(Integer keyCode) {
        Keyboard key = Keyboard.fromValue(keyCode);
        this.emptyCell = this.emptyCell.click(key);
    }
    
    @Override
    public Boolean checkVictory() {
        return IntStream.range(0, this.SIZE)
                .allMatch(index -> this.cells.get(index).getValue() == (index + 1));
    }
    
    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
	public Vertex getEmptyCell() {
		return emptyCell;
	}

}
