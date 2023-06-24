package game;

// Javax
import javax.swing.JButton;
import javax.swing.JOptionPane;

interface ISetText {
  void function(int linha, int coluna);
}

public class Actions {
  private JogoDosOito game;

  private int novaLinha;
  private int novaColuna;
  private int linhaVazia;
  private int colunaVazia;

  public Actions(JogoDosOito game) {
    this.game = game;
  }

  private void getNewPosition(int paraLinha, int paraColuna) {
    for (int linha = 0; linha < 3; linha++) {
      for (int coluna = 0; coluna < 3; coluna++) {
        if (game.getTabuleiro()[linha][coluna] == 0) {
          this.linhaVazia = linha;
          this.colunaVazia = coluna;
        }
      }
    }

    this.novaLinha = this.linhaVazia + paraLinha;
    this.novaColuna = this.colunaVazia + paraColuna;
  }

  public void mover(int paraLinha, int paraColuna) {
    int[][] tabuleiro = game.getTabuleiro();

    getNewPosition(paraLinha, paraColuna);

    // Movimento inválido
    if (this.novaLinha < 0 || this.novaLinha > 2 || this.novaColuna < 0 || this.novaColuna > 2) {
      return;
    }

    int newValue = tabuleiro[novaLinha][novaColuna];

    game.setTabuleiro(this.linhaVazia, this.colunaVazia, newValue);
    game.setTabuleiro(novaLinha, novaColuna, 0);

    atualizarTabuleiro();

    if (jogoConcluido()) {
      JOptionPane.showMessageDialog(game, "Parabéns, você venceu!");
      reiniciarJogo();
    }
  }

  private boolean jogoConcluido() {
    int count = 1;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (game.getTabuleiro()[i][j] != count % 9) {
          return false;
        }
        count++;
      }
    }
    return true;
  }

  public void atualizarTabuleiro() {
    ISetText setText = (int linha, int coluna) -> {
      JButton botao = game.getBotoes()[linha][coluna];
      int valor = game.getTabuleiro()[linha][coluna];

      if (valor == 0) {
        botao.setText("");
      } else {
        botao.setText(String.valueOf(valor));
      }
    };

    for (int linha = 0; linha < 3; linha++) {
      for (int coluna = 0; coluna < 3; coluna++) {
        setText.function(linha, coluna);
      }
    }
  }

  public void reiniciarJogo() {
    game.replaceTabuleiro(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } });
    atualizarTabuleiro();
  }
}
