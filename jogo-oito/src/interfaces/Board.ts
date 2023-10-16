import { AxisPair } from "../utils/PairOfAxis";
import { Matrix } from "./Matrix";

export interface Board {
  matrix: Matrix;

  get(): number[][];

  reset(): void;

  setWin(): void;

  checkVictory(): boolean;

  tryMoveNumToRight(axis: AxisPair): void;

  tryMoveNumToLeft(axis: AxisPair): void;

  tryMoveNumToUp(axis: AxisPair): void;

  tryMoveNumToDown(axis: AxisPair): void;

  moveNum(num: number): void;
}
