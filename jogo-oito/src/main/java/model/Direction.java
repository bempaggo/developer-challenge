package model;

import java.util.List;
import java.util.Optional;
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
        return Optional.of(direction)
                .filter(validDirection -> validDirection >= 0 && validDirection < conditions.size())
                .map(validDirection -> conditions.get(validDirection)
                .test(validDirection == 0 || validDirection == 1 ? col : row))
                .orElse(false);
    }
}
