package chat.gpt.model;

public class TabuleiroModel {
    private Integer[] posicoes;

    public TabuleiroModel(Integer[] posicoes) {
        this.posicoes = posicoes;
    }

    public TabuleiroModel() {
    }

    public Integer[] getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Integer[] posicoes) {
        this.posicoes = posicoes;
    }
}