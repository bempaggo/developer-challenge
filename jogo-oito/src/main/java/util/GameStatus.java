package util;

import interfaces.Graph;
import interfaces.Vertex;

import java.util.List;
import java.util.stream.IntStream;

public class GameStatus {

    private final Graph board;

    private GameStatus(Graph board) {
        this.board = board;
    }

    public static GameStatus of(Graph board) {
        return new GameStatus(board);
    }

    public Boolean isOver() {
        Integer size = getCells().size();
        return IntStream.range(0, size)
                .allMatch(index -> getCellValueByIndex(index) == (index + 1) % size);
    }

    private Integer getCellValueByIndex(Integer index) {
        return this.getCells().get(index).getValue();
    }

    private List<Vertex> getCells() {
        return this.board.getCells();
    }
}
