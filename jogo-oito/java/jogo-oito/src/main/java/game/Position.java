package game;

public class Position {
  private int linha;
  private int coluna;
  private int linhaVazia;
  private int colunaVazia;

  public Position(int linha, int coluna, int linhaVazia,
      int colunaVazia) {
    this.linha = linha;
    this.coluna = coluna;
    this.linhaVazia = linhaVazia;
    this.colunaVazia = colunaVazia;

  }

  public int getLinha() {
    return linha;
  }

  public int getColuna() {
    return coluna;
  }

  public int getLinhaVazia() {
    return linhaVazia;
  }

  public int getColunaVazia() {
    return colunaVazia;
  }
}
