export default class GameController {
  private boardMatrix: number[][];
  private victoryStatus: boolean;

  constructor() {
    this.boardMatrix = [
      [1, 2, 3],
      [8, 0, 4],
      [7, 5, 6],
    ];
    this.victoryStatus = false;
  }

  getVictoryStatus() {
    return this.victoryStatus;
  }

  getBoardMatrix() {
    return this.boardMatrix;
  }

  resetGame() {
    //.. todo
  }

  winGame() {
    // .. todo
  }

  private updateVictoryStatus() {
    const winBoardMatrix = [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 0],
    ];

    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (this.boardMatrix[i][j] !== winBoardMatrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  private getNumMatrixIndex(num: number) {
    for (let i = 0; i <= 2; i++) {
      for (let j = 0; j <= 2; j++) {
        if (this.boardMatrix[i][j] == num) {
          return [i, j];
        }
      }
    }
  }

  private setBoardMatrixNum(x: number, y: number, num: number) {
    this.boardMatrix[y][x] = num;
  }

  private tryMoveNum(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    const directionValue = this.boardMatrix[to.y][to.x];
    if (directionValue != null && directionValue == 0) {
      this.setBoardMatrixNum(to.x, to.y, num);
      this.setBoardMatrixNum(from.x, from.y, 0);
    }
  }

  private tryMoveNumToRight(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.x < 0) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumToLeft(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.x > 2) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumToUp(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.y < 0) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumToDown(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.y > 2) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  moveNum(num: number) {
    if (num == 0) return;
    const axis = this.getNumMatrixIndex(num);
    const from = {
      x: axis!![1],
      y: axis!![0],
    };

    this.tryMoveNumToLeft(from, { x: from.x - 1, y: from.y }, num);
    this.tryMoveNumToRight(from, { x: from.x + 1, y: from.y }, num);
    this.tryMoveNumToDown(from, { x: from.x, y: from.y + 1 }, num);
    this.tryMoveNumToUp(from, { x: from.x, y: from.y - 1 }, num);
    this.victoryStatus = this.updateVictoryStatus();
  }
}
