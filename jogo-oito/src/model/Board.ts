import { Axis } from "../model/Axis";

export default class Board {
  private matrix: number[][];
  private winMatrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0],
  ];

  constructor() {
    this.matrix = [
      [1, 2, 3],
      [8, 0, 4],
      [7, 5, 6],
    ];
  }

  getMatrix() {
    return this.matrix;
  }

  setBoardMatrix(toMatrix: number[][]) {
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        this.matrix[i][j] = toMatrix[i][j];
      }
    }
  }

  resetMatrix() {
    const resetMatrix = [
      [1, 2, 3],
      [8, 0, 4],
      [7, 5, 6],
    ];

    this.setBoardMatrix(resetMatrix);
  }

  setWinMatrix() {
    this.setBoardMatrix(this.winMatrix);
  }

  checkVictoryMatrix() {
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (this.matrix[i][j] !== this.winMatrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  getMatrixNumIndex(num: number) {
    let axis: number[] = [];
    for (let i = 0; i <= 2; i++) {
      for (let j = 0; j <= 2; j++) {
        if (this.matrix[i][j] == num) {
          axis = [i, j];
        }
      }
    }

    return axis;
  }

  private setMatrixNum(x: number, y: number, num: number) {
    this.matrix[y][x] = num;
  }

  private switchMatrixValue(axis: Axis, num: number) {
    const from = axis.from;
    const to = axis.to;

    const directionValue = this.matrix[to.y][to.x];
    if (directionValue != null && directionValue == 0) {
      this.setMatrixNum(to.x, to.y, num);
      this.setMatrixNum(from.x, from.y, 0);
    }
  }

  private tryMoveNumToRight(axis: Axis, num: number) {
    if (axis.to.x < 0) return;
    this.switchMatrixValue(axis, num);
  }

  private tryMoveNumToLeft(axis: Axis, num: number) {
    if (axis.to.x > 2) return;
    this.switchMatrixValue(axis, num);
  }

  private tryMoveNumToUp(axis: Axis, num: number) {
    if (axis.to.y < 0) return;
    this.switchMatrixValue(axis, num);
  }

  private tryMoveNumToDown(axis: Axis, num: number) {
    if (axis.to.y > 2) return;
    this.switchMatrixValue(axis, num);
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
  }
}
