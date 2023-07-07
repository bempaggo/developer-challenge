package chat.gpt.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    private final List<Integer> gridData;
    private final List<Integer> gameIsCompleteGridPattern;

    public Grid(int gridSize, boolean randomGrid) {
        this.gridData = createDefaultGridData(gridSize);
        this.gameIsCompleteGridPattern = createDefaultGridData(gridSize);
        if (randomGrid) {
            randomizeGridData();
        }
    }

    public List<Integer> getGridData() {
        return this.gridData;
    }

    public int getEmptyIndex() {
        return getGridData().indexOf(0);
    }

    public List<Integer> getGameIsCompleteGridPattern() {
        return gameIsCompleteGridPattern;
    }

    public void reset(int gridSize, boolean randomGrid) {
        gridData.clear();
    
        createDefaultGridData(gridSize);
        if (randomGrid) {
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