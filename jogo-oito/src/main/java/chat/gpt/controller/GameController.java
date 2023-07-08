package chat.gpt.controller;

import java.awt.event.KeyEvent;
import java.util.List;

import chat.gpt.exception.PressedKeyDoesNothingException;
import chat.gpt.model.Grid;
import chat.gpt.model.GridInterface;
import chat.gpt.util.GridConstants;
import chat.gpt.util.MessagePopUp;
import chat.gpt.view.GameGUI;

public class GameController implements ButtonActionListener, KeyboardListener {

    private GameService service;
    private GameGUI view;
    private GridInterface grid;

    public GameController(GameService service, GameGUI view, Grid grid) {
        this.service = service;
        this.view = view;
        this.grid = grid;
    }

    public void setView(GameGUI view) {
        this.view = view;
    }

    @Override
    public void processInput(int input) {
        move(input);
        view.updateGrid();
        if (gameIsComplete()) {
            MessagePopUp.showMessage("Parabéns, você venceu!");
        }
    }

    public void move(int keyCode) {
        try {
            switch (keyCode) {
                case KeyEvent.VK_UP -> service.moveUp();
                case KeyEvent.VK_DOWN -> service.moveDown();
                case KeyEvent.VK_LEFT -> service.moveLeft();
                case KeyEvent.VK_RIGHT -> service.moveRight();
                default -> throw new PressedKeyDoesNothingException();
            }
        } catch (PressedKeyDoesNothingException pressedKeyDoesNothingException) {
            MessagePopUp.showMessage(pressedKeyDoesNothingException.getMessage());
        }
    }

    @Override
    public void resetGame() {
        resetGrid();
        view.updateGrid();
    }

    public void resetGrid() {
        int gridSize = GridConstants.GRID_SIZE.getMeasure();
        boolean randomGrid = GridConstants.RANDOM_GRID.useRandomGrid();
        grid.reset(gridSize, randomGrid);
    }

    public boolean gameIsComplete() {
        return grid.getGridData().equals(grid.getGameIsCompleteGridPattern());
    }

    // temp method for not breaking things apart
    public List<Integer> gridActualState() {
        return grid.getGridData();
    }

}