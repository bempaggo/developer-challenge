import { Matrix } from "../interfaces/Matrix";
import { Axis } from "../utils/Axis";
import { AxisPair } from "../utils/PairOfAxis";

export class MatrixImpl implements Matrix {
  private matrix: number[][];
  private winMatrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0],
  ];
  private defaultMatrix = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];

  constructor() {
    this.matrix = structuredClone(this.defaultMatrix);
  }

  get() {
    return this.matrix;
  }

  set(toMatrix: number[][]) {
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        this.matrix[i][j] = toMatrix[i][j];
      }
    }
  }

  reset() {
    this.set(this.defaultMatrix);
  }

  setWin() {
    this.set(this.winMatrix);
  }

  checkVictory() {
    for (let i = 0; i < 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (this.matrix[i][j] !== this.winMatrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  getNumAxis(num: number) {
    let axis: Axis = new Axis(0, 0);

    for (let i = 0; i <= 2; i++) {
      for (let j = 0; j <= 2; j++) {
        if (this.matrix[i][j] == num) {
          axis = new Axis(j, i);
        }
      }
    }

    return axis;
  }

  private setNum(x: number, y: number, num: number) {
    this.matrix[y][x] = num;
  }

  switchValue(axis: AxisPair) {
    const from = axis.from;
    const to = axis.to;
    const fromValue = this.matrix[from.y][from.x];
    const toValue = this.matrix[to.y][to.x];

    if (toValue != null && toValue == 0) {
      this.setNum(to.x, to.y, fromValue);
      this.setNum(from.x, from.y, toValue);
    }
  }
}
