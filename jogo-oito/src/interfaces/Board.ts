import { Axis } from "../utils/Axis";
import { Matrix } from "./Matrix";

export interface Board {
  matrix: Matrix;

  get(): number[][];

  reset(): void;

  setWin(): void;

  checkVictory(): boolean;

  tryMoveNumToRight(axis: Axis, num: number): void;

  tryMoveNumToLeft(axis: Axis, num: number): void;

  tryMoveNumToUp(axis: Axis, num: number): void;

  tryMoveNumToDown(axis: Axis, num: number): void;

  moveNum(num: number): void;
}
