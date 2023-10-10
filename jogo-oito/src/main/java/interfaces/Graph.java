package interfaces;

import java.util.List;

/**
 * Interface que representa o grafo do jogo.
 *
 * @author quintino
 */
public interface Graph {
    void feedback();
    void setting();
    void swap(Integer keyCode);
    List<Vertex> getCells();
    Vertex getEmptyCell();
    void click(Integer cellValue);
    Boolean checkGameOver();
}
