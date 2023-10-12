/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package game.interfaces;

import java.util.List;

/**
 *
 * @author allen
 */
public interface Graph {
    
    void setting(Boolean feedback);

    void swap(Integer keyCode);

    List<Vertex> getCells();

    Vertex getEmptyCell();

    void click(Integer cellValue);

    Boolean checkVictory();
}
