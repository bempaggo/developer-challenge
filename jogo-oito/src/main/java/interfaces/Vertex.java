/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import enums.Keyboard;

import java.util.List;

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

    List<Edge> getAdjacents();
    
    void addAdjacents(Edge edge);

}
