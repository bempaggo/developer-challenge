package chat.gpt.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid implements GridInterface {

    private final List<Integer> gridData;
    private final List<Integer> gameIsCompleteGridPattern;
    private final int gridSize;
    private final boolean randomGrid;

    public Grid(int gridSize, boolean randomGrid) {
        this.randomGrid = randomGrid;
        this.gridSize = gridSize;
        this.gridData = createDefaultGridData(gridSize);
        this.gameIsCompleteGridPattern = createDefaultGridData(gridSize);
        if (randomGrid) {
            randomizeGridData();
        }
    }

    @Override
    public int getGridSize() {
        return gridSize;
    }

    @Override
    public int getGridWidth() {
        return (int) Math.sqrt(gridSize);
    }

    @Override
    public List<Integer> getGridData() {
        return this.gridData;
    }

    @Override
    public int getEmptySlotIndex() {
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
        if (this.randomGrid) {
            randomizeGridData();
        }
    }

    private List<Integer> createDefaultGridData(int gridSize) {
        List<Integer> gridData = IntStream.range(1, gridSize)
                .boxed()
                .collect(Collectors.toList());

        gridData.add(0);

        return gridData;
    }

    private void randomizeGridData() {
        Collections.shuffle(this.gridData);
    }


}