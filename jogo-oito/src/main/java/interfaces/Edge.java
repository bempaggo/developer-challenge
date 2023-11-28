package interfaces;

import model.Keyboard;

/**
 * Interface que representa uma aresta do jogo.
 *
 * @author quintino
 */
public interface Edge {
    Keyboard getKey();
    Vertex getCell();
}
