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

    void showSolvedBoard();

    void setUpNewBoard();

    void moveCellByKey(Integer keyCode);

    List<Vertex> getCells();

    void moveCellByValue(Integer cellValue);

    Boolean checkGameOver();
}
