import { expect, test } from "vitest";
import { MatrixImpl } from "../model/MatrixImpl";
import { Axis } from "../utils/Axis";
import { AxisPair } from "../utils/PairOfAxis";

test("should get a matrix with default values", () => {
  const matrix = new MatrixImpl();
  const defaultMatrix = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];

  expect(matrix.get()).toStrictEqual(defaultMatrix);
});

test("should set the matrix with the provided values", () => {
  const matrix = new MatrixImpl();
  const matrixProvided = [
    [1, 2, 3],
    [4, 6, 5],
    [7, 0, 8],
  ];

  matrix.set(matrixProvided);
  expect(matrix.get()).toStrictEqual(matrixProvided);
});

test("should reset matrix to default values", () => {
  const matrix = new MatrixImpl();
  const differentMatrix = [
    [1, 3, 2],
    [4, 6, 5],
    [8, 0, 7],
  ];
  const defaultMatrix = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];

  matrix.set(differentMatrix);
  matrix.reset();

  expect(matrix.get()).toStrictEqual(defaultMatrix);
});

test("should set matrix to winMatrix values", () => {
  const matrix = new MatrixImpl();
  const winMatrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0],
  ];

  matrix.setWin();
  expect(matrix.get()).toStrictEqual(winMatrix);
});

test("should check if matrix values is the same of winMatrix", () => {
  const matrix = new MatrixImpl();

  expect(matrix.checkVictory()).toBe(false);
  matrix.setWin();
  expect(matrix.checkVictory()).toBe(true);
});

test("should get the Axis of a value in matrix", () => {
  const matrix = new MatrixImpl();
  const zeroDefaultAxis = new Axis(1, 1);
  expect(matrix.getNumAxis(0)).toStrictEqual(zeroDefaultAxis);
});

test("should switch num value with zero value in matrix", () => {
  const matrix = new MatrixImpl();
  const from = matrix.getNumAxis(2);
  const to = matrix.getNumAxis(0);
  const axis = new AxisPair(from, to);

  matrix.switchValue(axis);

  const afterSwitchMatrix = [
    [1, 0, 3],
    [8, 2, 4],
    [7, 5, 6],
  ];

  expect(matrix.get()).toStrictEqual(afterSwitchMatrix);
});
