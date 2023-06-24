package game;

// Javax
import javax.swing.JButton;
import javax.swing.JOptionPane;

interface ISetText {
  void function(int row, int column);
}

public class Actions {
  private GameofEight game;

  private int newRow;
  private int newColumn;
  private int emptyRow;
  private int emptyColumn;

  public Actions(GameofEight game) {
    this.game = game;
  }

  private void getNewPosition(int toRow, int toColumn) {
    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        if (game.getGameBoard()[row][column] == 0) {
          this.emptyRow = row;
          this.emptyColumn = column;
        }
      }
    }

    this.newRow = this.emptyRow + toRow;
    this.newColumn = this.emptyColumn + toColumn;
  }

  public void move(int toRow, int toColumn) {
    int[][] gameBoard = game.getGameBoard();

    getNewPosition(toRow, toColumn);

    // Movimento inválido
    if (this.newRow < 0 || this.newRow > 2 || this.newColumn < 0 || this.newColumn > 2) {
      return;
    }

    int newValue = gameBoard[newRow][newColumn];

    game.setGameBoard(this.emptyRow, this.emptyColumn, newValue);
    game.setGameBoard(newRow, newColumn, 0);

    updateGameBoard();

    if (isCompleteGame()) {
      JOptionPane.showMessageDialog(game, "Parabéns, você venceu!");
      restartGame();
    }
  }

  private boolean isCompleteGame() {
    int count = 1;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (game.getGameBoard()[i][j] != count % 9) {
          return false;
        }
        count++;
      }
    }
    return true;
  }

  public void updateGameBoard() {
    ISetText setText = (int row, int column) -> {
      JButton button = game.getButtons()[row][column];
      int valor = game.getGameBoard()[row][column];

      if (valor == 0) {
        button.setText("");
      } else {
        button.setText(String.valueOf(valor));
      }
    };

    for (int row = 0; row < 3; row++) {
      for (int column = 0; column < 3; column++) {
        setText.function(row, column);
      }
    }
  }

  public void restartGame() {
    game.replaceGameBoard(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } });
    updateGameBoard();
  }
}
