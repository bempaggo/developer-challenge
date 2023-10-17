import { Axis } from "../utils/Axis";
import { Matrix } from "../interfaces/Matrix";
import { Board } from "../interfaces/Board";
import { AxisPair } from "../utils/PairOfAxis";

export class BoardImpl implements Board {
  private matrix: Matrix;

  constructor(matrix: Matrix) {
    this.matrix = matrix;
  }

  get() {
    return this.matrix.get();
  }

  reset() {
    this.matrix.reset();
  }

  setWin() {
    this.matrix.setWin();
  }

  checkVictory() {
    return this.matrix.checkVictory();
  }

  private tryMoveNumToRight(axis: AxisPair) {
    if (axis.to.x < 0) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToLeft(axis: AxisPair) {
    if (axis.to.x > 2) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToUp(axis: AxisPair) {
    if (axis.to.y < 0) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToDown(axis: AxisPair) {
    if (axis.to.y > 2) return;
    this.matrix.switchValue(axis);
  }

  moveNum(num: number) {
    if (num == 0) return;

    const fromAxis = this.matrix.getNumAxis(num);
    const toLeftAxis = new Axis(fromAxis.x - 1, fromAxis.y);
    const toRightAxis = new Axis(fromAxis.x + 1, fromAxis.y);
    const toDownAxis = new Axis(fromAxis.x, fromAxis.y + 1);
    const toUpAxis = new Axis(fromAxis.x, fromAxis.y - 1);

    this.tryMoveNumToLeft(new AxisPair(fromAxis, toLeftAxis));
    this.tryMoveNumToRight(new AxisPair(fromAxis, toRightAxis));
    this.tryMoveNumToDown(new AxisPair(fromAxis, toDownAxis));
    this.tryMoveNumToUp(new AxisPair(fromAxis, toUpAxis));
  }
}
