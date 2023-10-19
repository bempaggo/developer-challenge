/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import service.interfaces.Edge;
import service.interfaces.Cell;
import util.Keyboard;

import java.util.Objects;

/**
 *
 * @author allen
 */
public class EdgeImpl implements Edge{

    private final Keyboard key;
    private final Cell cell;

    public EdgeImpl(Keyboard key, Cell cell) {
        this.key = key;
        this.cell = cell;
    }

    @Override
    public Keyboard getKey() {
        return this.key;
    }

    @Override
    public Cell getCell() {
        return this.cell;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((EdgeImpl) obj).getKey(), this.getKey());
    }

}
