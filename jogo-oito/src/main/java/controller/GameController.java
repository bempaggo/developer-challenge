package controller;

import java.util.List;
import java.util.Optional;

import model.BoardInterface;
import util.BoardDataObserver;
import util.MessagePopUp;

public class GameController implements ControllerInterface, BoardDataObserver {

    private BoardInterface board;

    public GameController() {
    }

    public void setBoard(BoardInterface board) {
        this.board = board;
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

    @Override
    public void boardDataChanged(List<Integer> newBoardData) {
        Optional.of(gameIsComplete())
                .filter(Boolean::booleanValue)
                .ifPresent(isComplete -> MessagePopUp.showMessage("Parabéns, você venceu!"));
    }

    private Boolean gameIsComplete() {
        return board.getBoardData().equals(board.getGameIsCompleteBoardPattern());
    }

}