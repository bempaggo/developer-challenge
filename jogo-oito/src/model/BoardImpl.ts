import { Axis } from "../utils/Axis";
import { Matrix } from "../interfaces/Matrix";
import { Board } from "../interfaces/Board";

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

  private tryMoveNumToRight(axis: Axis) {
    if (axis.to.x < 0) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToLeft(axis: Axis) {
    if (axis.to.x > 2) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToUp(axis: Axis) {
    if (axis.to.y < 0) return;
    this.matrix.switchValue(axis);
  }

  private tryMoveNumToDown(axis: Axis) {
    if (axis.to.y > 2) return;
    this.matrix.switchValue(axis);
  }

  moveNum(num: number) {
    if (num == 0) return;
    const numAxis = this.matrix.getNumIndex(num);
    const from = {
      x: numAxis[1],
      y: numAxis[0],
    };

    this.tryMoveNumToLeft(new Axis(from, { x: from.x - 1, y: from.y }));
    this.tryMoveNumToRight(new Axis(from, { x: from.x + 1, y: from.y }));
    this.tryMoveNumToDown(new Axis(from, { x: from.x, y: from.y + 1 }));
    this.tryMoveNumToUp(new Axis(from, { x: from.x, y: from.y - 1 }));
  }
}
