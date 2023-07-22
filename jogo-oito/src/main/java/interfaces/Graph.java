/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author allen
 */
public interface Graph {

    void feedback();
    
    void setting();

    List<Vertex> getCells();

    Vertex getEmptyCell();

    void click(Integer cellValue);

    Boolean checkGameOver();
}
