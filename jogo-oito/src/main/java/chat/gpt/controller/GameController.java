package chat.gpt.controller;

import java.util.List;

import chat.gpt.model.GridInterface;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.GameGUI;

public class GameController implements ButtonActionListener {

    private GameGUI view;
    private GridInterface grid;

    public GameController(GameGUI view, GridInterface grid) {
        this.view = view;
        this.grid = grid;
    }

    public void setView(GameGUI view) {
        this.view = view;
    }

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

    public boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

    // temp method for not breaking things apart
    public List<Integer> gridActualState() {
        return grid.getGridData();
    }

}