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

  private tryMoveNumRight(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.x < 0) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumLeft(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.x > 2) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumUp(
    from: { x: number; y: number },
    to: { x: number; y: number },
    num: number,
  ) {
    if (to.y < 0) return;
    this.tryMoveNum(from, { x: to.x, y: to.y }, num);
  }

  private tryMoveNumDown(
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

    this.tryMoveNumLeft(from, { x: from.x - 1, y: from.y }, num);
    this.tryMoveNumRight(from, { x: from.x + 1, y: from.y }, num);
    this.tryMoveNumDown(from, { x: from.x, y: from.y + 1 }, num);
    this.tryMoveNumUp(from, { x: from.x, y: from.y - 1 }, num);
    this.victoryStatus = this.updateVictoryStatus();
  }
}
