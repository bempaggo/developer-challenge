/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.Edge;
import interfaces.Vertex;
import java.util.Objects;

/**
 *
 * @author allen
 */
public class Adjacent implements Edge{

    private final Keyboard key;
    private final Vertex cell;

    private Adjacent(Keyboard key, Vertex cell) {
        this.key = key;
        this.cell = cell;
    }

    public static Adjacent of(Keyboard key, Vertex cell) {
        return new Adjacent(key, cell);
    }

    @Override
    public Keyboard getKey() {
        return this.key;
    }

    @Override
    public Vertex getCell() {
        return this.cell;
    }

    @Override
    public Boolean cellValueIsEqual(Integer value) {
        return this.getCell().getValue().equals(value);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((Adjacent) obj).getKey(), this.getKey());
    }

}
