/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package game.interfaces;

import java.util.List;

import game.enums.Keyboard;
import game.model.Adjacent;
import game.model.Cell;

/**
 *
 * @author allen
 */
public interface Vertex {

    void setValue(Integer value);

    Integer getValue();

    void creatingHorizontalAdjacent(Cell cell);

    void creatingVerticalAdjacent(Cell cell);

    String valueToText();

    Adjacent getAdjacentByKeyCode(Keyboard key);

    Cell click(Keyboard key);

    List<Adjacent> getAdjacents();
    
    Cell swapCells(Integer value);

}
