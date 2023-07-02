package chat.gpt.infra.values;

import chat.gpt.model.Peca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TabuleiroPecas {
    LINHA_UM(1, 2, 3),
    LINHA_DOIS(4, 5, 6),
    LINHA_TRES(7, 8, 0);


    private final Peca pecaColuna1;
    private final Peca pecaColuna2;
    private final Peca pecaColuna3;

    TabuleiroPecas(Integer coluna1, Integer coluna2, Integer coluna3) {
        this.pecaColuna1 = new Peca(coluna1);
        this.pecaColuna2 = new Peca(coluna2);
        this.pecaColuna3 = new Peca(coluna3);
    }

    public static List<List<Peca>> pegaPecasTabuleiro() {
        return new ArrayList<>(Arrays.stream(TabuleiroPecas.values())
                .map(tabuleiroPecas -> Arrays.asList(tabuleiroPecas.pecaColuna1,
                        tabuleiroPecas.pecaColuna2, tabuleiroPecas.pecaColuna3))
                .toList());
    }
}
