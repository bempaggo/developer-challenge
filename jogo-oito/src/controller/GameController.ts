import { Board } from "../interfaces/Board";

export class GameController {
  private board: Board;
  private victoryStatus: boolean;

  constructor(board: Board) {
    this.board = board;
    this.victoryStatus = false;
  }

  getVictoryStatus() {
    return this.victoryStatus;
  }

  getBoard() {
    return this.board.get();
  }

  reset() {
    this.board.reset();
    this.victoryStatus = false;
  }

  win() {
    this.board.setWin();
    this.victoryStatus = true;
  }

  private updateVictoryStatus() {
    this.victoryStatus = this.board.checkVictory();
  }

  moveNum(num: number) {
    this.board.moveNum(num);
    this.updateVictoryStatus();
  }
}
