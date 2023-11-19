package controller;

import java.util.List;

import service.Graph;
import service.Vertex;
import service.serviceImpl.Board;

public class Controller {

    private final Graph board;

    public Controller() {
        board = new Board();
    }
    
    public void feedback() {
        board.feedback();
    }

    public void setting() {
        board.setting();
    }

    public List<Vertex> getCells() {
        return board.getCells();
    }

    public void swap(Integer keyCode) {
        board.swap(keyCode);
    }

    public Boolean checkGameOver() {
        return board.checkGameOver();

    }

    public void click(Integer cellValue) {
        board.click(cellValue);
    }

}
