/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.model;

import java.util.Objects;

import game.enums.Keyboard;
import game.interfaces.Edge;

/**
 *
 * @author allen
 */
public class Adjacent implements Edge{

    private final Keyboard key;
    private final Cell cell;

    public Adjacent(Keyboard key, Cell cell) {
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
        return Objects.equals(((Adjacent) obj).getKey(), this.getKey());
    }

}
