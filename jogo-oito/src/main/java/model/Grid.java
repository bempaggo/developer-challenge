package model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid implements GridInterface {

    private final List<Integer> gridData;
    private final List<Integer> gameIsCompleteGridPattern;
    private final Integer gridSize;
    private final Integer gridWidth;
    private final Boolean randomGrid;

    public Grid(Integer gridSize, Integer gridWidth, Boolean randomGrid) {
        this.randomGrid = randomGrid;
        this.gridSize = gridSize;
        this.gridWidth = gridWidth;
        this.gridData = createDefaultGridData(gridSize);
        this.gameIsCompleteGridPattern = List.copyOf(gridData);
        this.randomizeGridData(this.randomGrid);
    }

    @Override
    public Integer getGridSize() {
        return gridSize;
    }

    @Override
    public Integer getGridWidth() {
        return gridWidth;
    }

    @Override
    public List<Integer> getGridData() {
        return this.gridData;
    }

    @Override
    public Integer getEmptySlotIndex() {
        return getGridData().indexOf(0);
    }

    @Override
    public List<Integer> getGameIsCompleteGridPattern() {
        return gameIsCompleteGridPattern;
    }

    @Override
    public void reset() {
        gridData.clear();
        gridData.addAll(createDefaultGridData(this.gridSize));
        randomizeGridData(this.randomGrid);
    }

    private List<Integer> createDefaultGridData(int gridSize) {
        List<Integer> gridData = IntStream.range(1, gridSize)
                .boxed()
                .collect(Collectors.toList());
        gridData.add(0);
        return gridData;
    }

    private void randomizeGridData(Boolean randomGrid) {
        Optional.ofNullable(randomGrid)
                .filter(Boolean::booleanValue)
                .ifPresent(ignored -> Collections.shuffle(this.gridData));
    }

}