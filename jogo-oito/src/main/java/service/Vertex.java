package service;

import java.util.List;

import model.enumType.Keyboard;

public interface Vertex {

    void creatingHorizontalAdjacent(Vertex cell);

    void creatingVerticalAdjacent(Vertex cell);

    String valueToText();

    Edge getAdjacentByKeyCode(Keyboard key);

    Vertex click(Keyboard key);

    List<Edge> getAdjacents();
    
    Vertex swapCells(Integer value);

	Integer getValue();

	void addAdjacents(Edge edge);

	void setValue(Integer value);

}
