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

    void loadCells();

    void swap(Integer keyCode);

    List<Vertex> getCells();

    void defineCellRelationships();

    Vertex getEmptyCell();

    void click(Integer cellValue);

    Boolean checkGameOver();
}
