package controller;

import java.util.List;

import model.GridInterface;
import util.MessagePopUp;
import view.GameUI;


public class GameController implements ControllerInterface {

    private GridInterface grid;
    private GameUI view;

    public GameController() {
    }

    public void setGrid(GridInterface grid) {
        this.grid = grid;
    }

    public void setView(GameUI view) {
        this.view = view;
    }

    @Override
    public void notifyMove() {
        view.updateGrid();
        if (gameIsComplete()) {
            MessagePopUp.showMessage("Parabéns, você venceu!");
        }
    }

    @Override
    public void resetGame() {
        grid.reset();
        view.updateGrid();
    }

    @Override
    public List<Integer> gridData() {
        return grid.getGridData();
    }

    private boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

}