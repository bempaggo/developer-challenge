import { Axis } from "../utils/Axis";
import { AxisPair } from "../utils/PairOfAxis";

export interface Matrix {
  matrix: number[][];
  winMatrix: number[][];
  defaultMatrix: number[][];

  get(): number[][];

  set(toMatrix: number[][]): void;

  reset(): void;

  setWin(): void;

  checkVictory(): boolean;

  getNumAxis(num: number): Axis;

  setNum(x: number, y: number, num: number): void;

  switchValue(axis: AxisPair): void;
}
