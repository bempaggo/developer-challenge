/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import enums.Keyboard;

/**
 *
 * @author allen
 */
public interface Edge {

    Keyboard getKey();

    Vertex getCell();

    Boolean cellValueIsEqual(Integer value);
}
