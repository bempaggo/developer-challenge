/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.Keyboard;

import java.util.List;

/**
 *
 * @author allen
 */
public interface Vertex extends Cloneable {

    void setValue(Integer value);

    Integer getValue();

    String valueToText();

    Vertex performSwap(Keyboard key);
    
    void addAdjacents(Edge edge);

    Vertex performSwap(Integer value);

    List<Edge> getAdjacents();

    Vertex clone();
}
