package interfaces;

import java.util.Map;

import model.Keyboard;

public interface Vertex {

  void setValue(Integer value);

  Integer getValue();

  void creatingHorizontalAdjacent(Vertex cell);

  void creatingVerticalAdjacent(Vertex cell);

  String valueToText();

  Vertex swapByAdjacentCellKey(Keyboard key);

  Vertex swapByAdjacentCellValue(Integer value);

  Map<Keyboard, Vertex> getAdjacents();

  boolean equals(Object obj);  

  Vertex getAdjacentByKeyCode(Keyboard key);

}