package controller;

import service.interfaces.Cell;
import java.util.List;
import service.GraphImpl;

public class Controller {

    private final service.interfaces.Graph graph;

    public Controller() {
        this.graph = new GraphImpl();
    }
    
    public void feedback() {
        this.graph.feedback();
    }

    public void setter() {
        this.graph.setter();
    }

    public List<Cell> getCells() {
        return this.graph.getCells();
    }

    public void swap(Integer keyCode) {
        this.graph.swap(keyCode);
    }

    public Boolean checkGameOver() {
        return this.graph.checkGameOver();

    }

    public void click(Integer cellValue) {
        this.graph.click(cellValue);
    }

}
