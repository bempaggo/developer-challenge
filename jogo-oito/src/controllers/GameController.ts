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
