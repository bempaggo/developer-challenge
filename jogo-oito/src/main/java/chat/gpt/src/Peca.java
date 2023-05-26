package chat.gpt.src;

public class Peca {

    int valor;

    public Peca(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Peca peca = (Peca) o;

        return valor == peca.valor;
    }

    @Override
    public int hashCode() {
        return valor;
    }
}
