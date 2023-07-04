package chat.gpt.model;

public class TabuleiroModel {
    private int[] posicoes;

    public TabuleiroModel(int[] posicoes) {
        this.posicoes = posicoes;
    }

    public TabuleiroModel() {
    }

    public int[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(int[] posicoes) {
        this.posicoes = posicoes;
    }
}