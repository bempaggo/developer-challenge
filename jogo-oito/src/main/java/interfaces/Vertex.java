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

    Vertex swapByKeycode(Keyboard key);
    
    void addAdjacents(Edge edge);
    
    Vertex swapByCellValue(Integer value);

}
