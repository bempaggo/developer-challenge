import Board from "../model/Board";

export default class GameController {
  private board: Board;
  private victoryStatus: boolean;

  constructor() {
    this.board = new Board();
    this.victoryStatus = false;
  }

  getVictoryStatus() {
    return this.victoryStatus;
  }

  getBoard() {
    return this.board.getMatrix();
  }

  resetGame() {
    this.board.resetMatrix();
    this.victoryStatus = false;
  }

  winGame() {
    this.board.setWinMatrix();
    this.victoryStatus = true;
  }

  private updateVictoryStatus() {
    this.victoryStatus = this.board.checkVictoryMatrix();
  }

  moveNum(num: number) {
    this.board.moveNum(num);
    this.updateVictoryStatus();
  }
}
