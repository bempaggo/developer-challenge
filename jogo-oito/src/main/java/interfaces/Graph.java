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

    void gameSolutionBoardState();
    
    void gameStartBoardState();

    void swap(Integer keyCode);

    List<Vertex> getCells();

    void click(Integer cellValue);

    Boolean isGameComplete();

    void addListener(BoardUpdateListener listener);
}
