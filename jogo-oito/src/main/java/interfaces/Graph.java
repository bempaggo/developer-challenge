/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import listeners.BoardUpdateListener;

import java.util.List;

/**
 *
 * @author allen
 */
public interface Graph {

    void setBoardAsSolved();

    void initializeBoard();

    void keyPressed(Integer keyCode);
    List<Vertex> getCells();

    void buttonClicked(Integer cellValue);
    Boolean isGameComplete();
    void addListener(BoardUpdateListener listener);
}
