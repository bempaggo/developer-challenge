package interfaces;

import java.util.List;
import model.Keyboard;

/**
 * Interface que representa um vértice do jogo.
 *
 * @author quintino
 */
public interface Vertex {
    void setValue(Integer value);
    Integer getValue();
    void creatingHorizontalAdjacent(Vertex cell);
    void creatingVerticalAdjacent(Vertex cell);
    String valueToText();
    Edge getAdjacentByKeyCode(Keyboard key);
    Vertex click(Keyboard key);
    List<Edge> getAdjacents();
    void addAdjacents(Edge edge);
    Vertex swapCells(Integer value);
}
