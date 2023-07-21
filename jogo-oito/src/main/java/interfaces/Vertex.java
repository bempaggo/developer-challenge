package interfaces;

import java.util.Map;

import model.Keyboard;

public interface Vertex {

  void setValue(Integer value);

  Integer getValue();

  Vertex getAdjacentByKeyCode(Keyboard key);
  
  Vertex getAdjacentByValue(Integer value);

  void creatingHorizontalAdjacent(Vertex cell);

  void creatingVerticalAdjacent(Vertex cell);

  String valueToText();

  Vertex swapCells(Vertex movementCell);

  Map<Keyboard, Vertex> getAdjacents();

  boolean equals(Object obj);  

  
}