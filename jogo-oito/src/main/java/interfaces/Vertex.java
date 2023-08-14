/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import model.Keyboard;

/**
 *
 * @author allen
 */
public interface Vertex {

    void setValue(Integer value);

    Integer getValue();

    String valueToText();

    Edge getAdjacentByKeyCode(Keyboard key);

    Vertex click(Keyboard key);
    
    void addAdjacents(Edge edge);
    
    Vertex swapCells(Integer value);

}
