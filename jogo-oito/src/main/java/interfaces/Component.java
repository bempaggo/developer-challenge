package interfaces;

import model.Keyboard;

import java.util.List;

public interface Component {

    List<Vertex> getComponents();

    Vertex getComponent(Integer cellValue);

    void performSwap(Integer cellValue);

    void performSwap(Keyboard key);

}
