import { Axis } from "../model/Axis";

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
    const winMatrix = [
      [1, 2, 3],
      [4, 5, 6],
      [7, 8, 0],
    ];

    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (this.boardMatrix[i][j] !== winMatrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  private getMatrixNumIndex(num: number) {
    let axis: number[] = [];
    for (let i = 0; i <= 2; i++) {
      for (let j = 0; j <= 2; j++) {
        if (this.boardMatrix[i][j] == num) {
          axis = [i, j];
        }
      }
    }

    return axis;
  }

  private setMatrixNum(x: number, y: number, num: number) {
    this.boardMatrix[y][x] = num;
  }

  private tryMoveNum(axis: Axis, num: number) {
    const from = axis.from;
    const to = axis.to;

    const directionValue = this.boardMatrix[to.y][to.x];
    if (directionValue != null && directionValue == 0) {
      this.setMatrixNum(to.x, to.y, num);
      this.setMatrixNum(from.x, from.y, 0);
    }
  }

  private tryMoveNumToRight(axis: Axis, num: number) {
    if (axis.to.x < 0) return;
    this.tryMoveNum(axis, num);
  }

  private tryMoveNumToLeft(axis: Axis, num: number) {
    if (axis.to.x > 2) return;
    this.tryMoveNum(axis, num);
  }

  private tryMoveNumToUp(axis: Axis, num: number) {
    if (axis.to.y < 0) return;
    this.tryMoveNum(axis, num);
  }

  private tryMoveNumToDown(axis: Axis, num: number) {
    if (axis.to.y > 2) return;
    this.tryMoveNum(axis, num);
  }

  moveNum(num: number) {
    if (num == 0) return;
    const axis = this.getMatrixNumIndex(num);
    const from = {
      x: axis[1],
      y: axis[0],
    };

    this.tryMoveNumToLeft(new Axis(from, { x: from.x - 1, y: from.y }), num);
    this.tryMoveNumToRight(new Axis(from, { x: from.x + 1, y: from.y }), num);
    this.tryMoveNumToDown(new Axis(from, { x: from.x, y: from.y + 1 }), num);
    this.tryMoveNumToUp(new Axis(from, { x: from.x, y: from.y - 1 }), num);
    this.victoryStatus = this.updateVictoryStatus();
  }
}
