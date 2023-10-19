/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.interfaces;

import java.util.List;
import util.Keyboard;

/**
 *
 * @author allen
 */
public interface Cell {

    void setValue(Integer value);

    Integer getValue();

    void createHorizontalAdjacent(Cell cell);

    void createVerticalAdjacent(Cell cell);

    String valueToText();

    Edge getAdjacentByKeyCode(Keyboard key);

    Cell click(Keyboard key);

    List<Edge> getAdjacents();
    
    void addAdjacent(Edge edge);
    
    Cell swapCells(Integer value);

}
