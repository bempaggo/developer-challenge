package chat.gpt.model;

import static chat.gpt.view.Constantes.*;

public class Tabuleiro {

    private int[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new int[][] { 
            {UM, DOIS, TRES},
            {QUATRO, CINCO, SEIS},
            {SETE, OITO, VAZIO} 
        };
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

}
