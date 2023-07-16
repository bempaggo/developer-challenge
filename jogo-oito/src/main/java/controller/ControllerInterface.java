package controller;

import java.util.List;

import model.BoardInterface;
import view.GameUI;

public interface ControllerInterface {

    void notifyMove();

    void resetGame();

    List<Integer> boardData();

    void setView(GameUI view);

    void setBoard(BoardInterface board);

    void boardSolution();

}