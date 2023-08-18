package factories;

import interfaces.Edge;
import interfaces.Vertex;
import model.*;

public class GameFactoryImpl implements GameFactory {

    public GameFactoryImpl () {}
    @Override
    public Vertex createCell() {
        return new Cell();
    }

    @Override
    public Edge createAdjacentUp(Vertex cell) { return new Adjacent(Keyboard.UP, cell); }

    @Override
    public Edge createAdjacentDown(Vertex cell) { return new Adjacent(Keyboard.DOWN, cell); }

    @Override
    public Edge createAdjacentLeft(Vertex cell) {
        return new Adjacent(Keyboard.LEFT, cell);
    }

    @Override
    public Edge createAdjacentRight(Vertex cell) {
        return new Adjacent(Keyboard.RIGHT, cell);
    }

    @Override
    public Matrix createMatrix() {
        return new Matrix(this);
    }

}
