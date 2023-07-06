/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author allen
 */
public class Edge {

    private final Keyboard key;
    private final Cell cell;

    public Edge(Keyboard key, Cell cell) {
        this.key = key;
        this.cell = cell;
    }

    public Keyboard getKey() {
        return this.key;
    }

    public Cell getCell() {
        return this.cell;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((Edge) obj).getKey(), this.getKey());
    }

}
