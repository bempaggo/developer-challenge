package chat.gpt.infra.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TabuleiroPecas {
    LINHA_UM(1, 2, 3),
    LINHA_DOIS(4, 5, 6),
    LINHA_TRES(7, 8, 0);


    private final int coluna1;
    private final int coluna2;
    private final int coluna3;

    TabuleiroPecas(int coluna1, int coluna2, int coluna3) {
        this.coluna1 = coluna1;
        this.coluna2 = coluna2;
        this.coluna3 = coluna3;
    }

    public static List<List<Integer>> pegaPecasTabuleiro() {
        return new ArrayList<>(Arrays.stream(TabuleiroPecas.values())
                .map(tabuleiroPecas -> Arrays.asList(tabuleiroPecas.coluna1,
                        tabuleiroPecas.coluna2, tabuleiroPecas.coluna3))
                .toList());
    }
}
