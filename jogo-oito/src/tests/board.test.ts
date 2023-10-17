import { expect, test } from "vitest";
import { MatrixImpl } from "../model/MatrixImpl";
import { BoardImpl } from "../model/BoardImpl";

test("should get board", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);

  expect(board.get()).toStrictEqual(matrix.get());
});

test("should reset board", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const defaultBoard = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];
  const afterMoveBoard = [
    [1, 0, 3],
    [8, 2, 4],
    [7, 5, 6],
  ];

  board.moveNum(2);
  expect(board.get()).toStrictEqual(afterMoveBoard);

  board.reset();
  expect(board.get()).toStrictEqual(defaultBoard);
});

test("should set board to win positions", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const winBoard = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0],
  ];

  board.setWin();
  expect(board.get()).toStrictEqual(winBoard);
});

test("should check if board is in victory positions", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);

  expect(board.checkVictory()).toBe(false);
  board.setWin();
  expect(board.checkVictory()).toBe(true);
});

test("should move a num to left", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const afterMoveBoard = [
    [1, 2, 3],
    [8, 4, 0],
    [7, 5, 6],
  ];

  board.moveNum(4);
  expect(board.get()).toStrictEqual(afterMoveBoard);
});

test("should move a num to right", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const afterMoveBoard = [
    [1, 2, 3],
    [0, 8, 4],
    [7, 5, 6],
  ];

  board.moveNum(8);
  expect(board.get()).toStrictEqual(afterMoveBoard);
});

test("should move a num to top", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const afterMoveBoard = [
    [1, 2, 3],
    [8, 5, 4],
    [7, 0, 6],
  ];

  board.moveNum(5);
  expect(board.get()).toStrictEqual(afterMoveBoard);
});

test("should move a num to down", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const afterMoveBoard = [
    [1, 0, 3],
    [8, 2, 4],
    [7, 5, 6],
  ];

  board.moveNum(2);
  expect(board.get()).toStrictEqual(afterMoveBoard);
});
