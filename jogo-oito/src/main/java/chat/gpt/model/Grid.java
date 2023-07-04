package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import chat.gpt.exception.GridDoesNotFeatStandardsException;

public class Grid {

    private final List<Integer> grid;

    public Grid(List<Integer> gridData) {
        gridValidate(gridData);
        this.grid = gridData;
    }

    public Grid() {
        this(createDefaultGridData());
    }

    public List<Integer> getGrid() {
        return grid;
    }

    private void gridValidate(List<Integer> gridData) {
        if (!validSize(gridData) || !noRepeatedElements(gridData)) {
            throw new GridDoesNotFeatStandardsException();
        }
    }

    private boolean validSize(List<Integer> gridData) {
        return gridData.size() == GRID_AREA;
    }

    private boolean noRepeatedElements(List<Integer> gridData) {
        Set<Integer> uniqueElements = new HashSet<>(gridData);
        return uniqueElements.size() == GRID_AREA;
    }

    private static List<Integer> createDefaultGridData() {
        List<Integer> gridData = Arrays.stream(DEFAULT_MODE)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(gridData);
        return gridData;
    }

    public int getEmptyIndex() {
        return getGrid().indexOf(EMPTY);
    }

}