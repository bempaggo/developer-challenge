package interfaces;

import java.util.Map;

import model.Keyboard;

public interface Vertex {

  void setValue(Integer value);

  Integer getValue();

  void creatingHorizontalAdjacent(Vertex cell);

  void creatingVerticalAdjacent(Vertex cell);

  String valueToText();

  Vertex click(Keyboard key);

  Vertex swapCells(Integer value);

  Map<Keyboard, Vertex> getAdjacents();

  boolean equals(Object obj);  

}