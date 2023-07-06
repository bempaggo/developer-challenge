package model;

import java.util.List;
import java.util.function.Predicate;

public class Direction {

    private final Integer NUM_ROWS;
    private final Integer NUM_COLS;
    private final List<Predicate<Integer>> conditions;

    public Direction(Integer NUM_ROWS, Integer NUM_COLS) {
        this.NUM_ROWS = NUM_ROWS;
        this.NUM_COLS = NUM_COLS;

        this.conditions = List.of(
                col -> col > 0,
                col -> col < this.NUM_COLS - 1,
                row -> row > 0,
                row -> row < this.NUM_ROWS - 1
        );
    }

    public Boolean isValidDirection(Integer row, Integer col, Integer direction) {
        if (direction >= 0 && direction < conditions.size()) {
            return conditions.get(direction).test(direction == 0 || direction == 1 ? col : row);
        }
        return false;
    }
}
