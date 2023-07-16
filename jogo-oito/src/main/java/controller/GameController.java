package controller;

import java.util.List;

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
        view.updateBoard();
        if (gameIsComplete()) {
            MessagePopUp.showMessage("Parabéns, você venceu!");
        }
    }

    @Override
    public void boardSolution() {
        board.solution();
        view.updateBoard();
    }

    @Override
    public void resetGame() {
        board.reset();
        view.updateBoard();
    }

    @Override
    public List<Integer> boardData() {
        return board.getBoardData();
    }

    private boolean gameIsComplete() {
        return board.getBoardData().equals(board.getGameIsCompleteBoardPattern());
    }


}