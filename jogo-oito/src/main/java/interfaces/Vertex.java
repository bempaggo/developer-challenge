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
public interface Vertex extends Cloneable, Component {

    void setValue(Integer value);

    Integer getValue();

    String valueToText();

    void addAdjacents(Edge edge);

    List<Edge> getAdjacents();

    Vertex clone();
}
