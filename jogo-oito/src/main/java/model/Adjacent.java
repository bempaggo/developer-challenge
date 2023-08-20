package model;

import interfaces.Edge;
import interfaces.Vertex;

public record Adjacent(Keyboard key, Vertex cell) implements Edge {
}
