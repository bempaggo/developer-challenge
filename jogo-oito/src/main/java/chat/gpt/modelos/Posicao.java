package chat.gpt.modelos;

public class Posicao {

    int linha;
    int coluna;

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posicao posicao = (Posicao) o;

        if (linha != posicao.linha) return false;
        return coluna == posicao.coluna;
    }

    @Override
    public int hashCode() {
        int result = linha;
        result = 31 * result + coluna;
        return result;
    }
}
