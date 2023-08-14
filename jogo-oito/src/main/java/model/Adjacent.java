/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.Edge;
import interfaces.Vertex;
import java.util.Objects;

/**
 * @author allen
 */
public record Adjacent(Keyboard key, Vertex cell) implements Edge {

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(((Adjacent) obj).key(), this.key());
    }

}
