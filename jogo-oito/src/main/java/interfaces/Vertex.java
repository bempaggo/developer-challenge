/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import enums.Keyboard;

/**
 *
 * @author allen
 */
public interface Vertex {

    void setValue(Integer value);

    Integer getValue();

    void createHorizontalAdjacent(Vertex cell);

    void createVerticalAdjacent(Vertex cell);

    String valueToText();

    Edge getAdjacentByKeyCode(Keyboard key);

    Vertex click(Keyboard key);

    List<Edge> getAdjacents();
    
    void addAdjacents(Edge edge);
    
    Vertex swapCells(Integer value);

}
