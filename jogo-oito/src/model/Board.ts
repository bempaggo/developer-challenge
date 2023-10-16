import { Axis } from "../model/Axis";
import Matrix from "./Matrix";

export default class Board {
  private matrix: Matrix;

  constructor() {
    this.matrix = new Matrix();
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

  private tryMoveNumToRight(axis: Axis, num: number) {
    if (axis.to.x < 0) return;
    this.matrix.switchValue(axis, num);
  }

  private tryMoveNumToLeft(axis: Axis, num: number) {
    if (axis.to.x > 2) return;
    this.matrix.switchValue(axis, num);
  }

  private tryMoveNumToUp(axis: Axis, num: number) {
    if (axis.to.y < 0) return;
    this.matrix.switchValue(axis, num);
  }

  private tryMoveNumToDown(axis: Axis, num: number) {
    if (axis.to.y > 2) return;
    this.matrix.switchValue(axis, num);
  }

  moveNum(num: number) {
    if (num == 0) return;
    const axis = this.matrix.getNumIndex(num);
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
