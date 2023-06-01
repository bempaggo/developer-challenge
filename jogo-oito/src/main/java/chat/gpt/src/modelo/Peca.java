package chat.gpt.src.modelo;

public class Peca {

    private final Integer valor;
    private Integer posicao;

    public Peca(Integer valor, Integer posicao) {
        this.valor = valor;
        this.posicao = posicao;
    }

    public Integer getValor() {
        return valor;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public String getTextoValor() {
        return this.valor == 9 ? " " : this.valor.toString();
    }

    public Boolean ehPecaVazia() {
        return this.valor == 9;
    }

    public Boolean posicaoCorreta() {
        return this.getPosicao().equals(this.getValor() - 1);
    }
}
