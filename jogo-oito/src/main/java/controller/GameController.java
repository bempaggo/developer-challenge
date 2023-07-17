package controller;

import java.util.List;
import java.util.Optional;

import model.BoardInterface;
import util.MessagePopUp;
import view.GameUI;

public class GameController implements ControllerInterface {

    private BoardInterface board;
    private GameUI view;

    public GameController() {
    }

    public void setBoard(BoardInterface board) {
        this.board = board;
    }

    public void setView(GameUI view) {
        this.view = view;
    }

    @Override
    public void notifyMove() {   
        Optional.of(gameIsComplete())
                .filter(Boolean::booleanValue)
                .ifPresent(isComplete -> MessagePopUp.showMessage("Parabéns, você venceu!"));
    }

    @Override
    public void boardSolution() {
        board.solution();
    }

    @Override
    public void resetGame() {
        board.reset();
    }

    @Override
    public List<Integer> boardData() {
        return board.getBoardData();
    }

    private boolean gameIsComplete() {
        return board.getBoardData().equals(board.getGameIsCompleteBoardPattern());
    }


}