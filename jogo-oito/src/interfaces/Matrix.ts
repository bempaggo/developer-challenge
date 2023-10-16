import { Axis } from "../utils/Axis";

export interface Matrix {
  matrix: number[][];
  winMatrix: number[][];
  defaultMatrix: number[][];

  get(): number[][];

  set(toMatrix: number[][]): void;

  reset(): void;

  setWin(): void;

  checkVictory(): boolean;

  getNumIndex(num: number): number[];

  setNum(x: number, y: number, num: number): void;

  switchValue(axis: Axis, num: number): void;
}
