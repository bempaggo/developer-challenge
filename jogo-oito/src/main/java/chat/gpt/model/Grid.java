package chat.gpt.model;

import static chat.gpt.util.Constants.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    private final List<Integer> grid;

    public Grid() {
        this.grid = createDefaultGridData();
    }

    public List<Integer> getGrid() {
        return grid;
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

    private static List<Integer> createDefaultGridData() {
        List<Integer> gridData = IntStream.range(0, GRID_AREA)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(gridData);
        return gridData;
    } 

    public int getEmptyIndex() {
        return getGrid().indexOf(0);
    }

}