package factories;

import interfaces.BoardComponent;
import interfaces.Edge;
import interfaces.Vertex;
import model.Adjacent;
import model.Cell;
import model.Keyboard;
import model.Matrix;

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
    public BoardComponent createMatrix() {
        return new Matrix(this);
    }

}
