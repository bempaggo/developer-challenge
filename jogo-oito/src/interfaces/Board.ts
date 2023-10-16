import { Axis } from "../utils/Axis";
import { Matrix } from "./Matrix";

export interface Board {
  matrix: Matrix;

  get(): number[][];

  reset(): void;

  setWin(): void;

  checkVictory(): boolean;

  tryMoveNumToRight(axis: Axis): void;

  tryMoveNumToLeft(axis: Axis): void;

  tryMoveNumToUp(axis: Axis): void;

  tryMoveNumToDown(axis: Axis): void;

  moveNum(num: number): void;
}
