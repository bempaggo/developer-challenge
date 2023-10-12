/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import game.interfaces.IMatrix;
import game.interfaces.Vertex;

/**
 *
 * @author allen
 */
public final class Matrix implements IMatrix {

    private List<Vertex> cells;
    private Vertex emptyCell;
    private List<Integer> values;
    private Integer size;

    public Matrix() {
        this.cells = new ArrayList<>();
        this.values = new ArrayList<>();
    }
    
    @Override
    public void createCells(Boolean feedback, Integer order) {
    	this.createValues(order);
    	
    	if (!feedback) {
    		Collections.shuffle(values);
    	}
    	
    	IntStream.range(0, this.size).forEach(index -> this.cells.add(new Cell(this.values.get(index))));
    	this.defineAdjacent(order);
    	this.defineEmptyCell();
    }
    
    private void createValues(Integer order) {
    	this.size = order*order;
    	IntStream.range(0, this.size).forEach(index -> this.values.add(index+1));
    	this.values.set(size-1, 0);
    }
    
    private void defineAdjacent(Integer order) {
    	Integer multiple = order * (order-1);
    	IntStream.range(0, this.size).forEach(index -> {
    		Integer rest = index % order;
    		if (!rest.equals(order -1))
    			this.cells.get(index).creatingHorizontalAdjacent(this.cells.get(index+1));
    		if (index < multiple)
    			this.cells.get(index).creatingVerticalAdjacent(this.cells.get(index+order));
    	});
    }
    
    private void defineEmptyCell() {
        Optional<Vertex> minCell = this.cells.stream()
                .min(Comparator.comparing(cell -> cell.getValue()));
        minCell.ifPresent(cell -> {
            this.setEmptyCell(cell);
        });
    }
    
    @Override
    public List<Vertex> getCells() {
        return this.cells;
    }

    @Override
	public Vertex getEmptyCell() {
		return emptyCell;
	}

    @Override
	public void setEmptyCell(Vertex emptyCell) {
		this.emptyCell = emptyCell;
	}

}
