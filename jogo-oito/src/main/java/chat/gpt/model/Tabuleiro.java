package chat.gpt.model;

public class Tabuleiro {

    private int[][] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }
}
