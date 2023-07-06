package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    private final List<Integer> gridData;
    private final List<Integer> gameIsCompleteGridPattern;

    public Grid() {
        this.gridData = this.randomizeGridData(createDefaultGridData());
        this.gameIsCompleteGridPattern = createDefaultGridData();
    }

    public List<Integer> getGridData() {
        return gridData;
    }

    /* Ao invés desses desses métodos eu posso simplesmente fazer testes unitários
    para verificar se a grid está de acordo com os padrões
    
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
    } */

    private List<Integer> createDefaultGridData() {
        List<Integer> gridData = IntStream.range(0, GRID_AREA)
                .boxed()
                .collect(Collectors.toList());
        return gridData;
    } 
    
    private List<Integer> randomizeGridData(List<Integer> gridData) {
        Collections.shuffle(gridData);
        return gridData;
    }

    public int getEmptyIndex() {
        return getGridData().indexOf(0);
    }

    public List<Integer> getGameIsCompleteGridPattern() {
        return gameIsCompleteGridPattern;
    }
}