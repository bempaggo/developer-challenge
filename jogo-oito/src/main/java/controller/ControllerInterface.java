package controller;

import java.util.List;

import model.GridInterface;
import view.GameUI;

public interface ControllerInterface {

    void notifyMove();

    void resetGame();

    List<Integer> gridData();

    void setView(GameUI view);

    void setGrid(GridInterface grid);

}