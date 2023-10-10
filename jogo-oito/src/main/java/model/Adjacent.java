package model;

import interfaces.Edge;
import interfaces.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * Classe que representa uma aresta do jogo.
 *
 * @author quintino
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Adjacent implements Edge {
    private final Keyboard key;
    private final Vertex cell;
}
