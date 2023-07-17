package controller;

import java.util.List;

import model.BoardInterface;

public interface ControllerInterface {

    void resetGame();

    List<Integer> boardData();

    void setBoard(BoardInterface board);

    void boardSolution();

}