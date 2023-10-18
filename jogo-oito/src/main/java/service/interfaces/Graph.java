/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.interfaces;

import java.util.List;

/**
 *
 * @author allen
 */
public interface Graph {

    void feedback();
    
    void setter();

    void swap(Integer keyCode);

    List<Cell> getCells();

    Cell getEmptyCell();

    void click(Integer cellValue);

    Boolean checkGameOver();
}
