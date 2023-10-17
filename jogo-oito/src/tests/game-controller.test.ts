import { expect, test } from "vitest";
import { MatrixImpl } from "../model/MatrixImpl";
import { BoardImpl } from "../model/BoardImpl";
import { GameController } from "../controller/GameController";

test("should get board", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const defaultBoard = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];

  expect(controller.getBoard()).toStrictEqual(defaultBoard);
});

test("should get victory status", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);

  expect(controller.getVictoryStatus()).toBe(false);
});

test("should reset board", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const afterMoveBoard = [
    [1, 0, 3],
    [8, 2, 4],
    [7, 5, 6],
  ];
  const defaultBoard = [
    [1, 2, 3],
    [8, 0, 4],
    [7, 5, 6],
  ];

  board.moveNum(2);
  expect(controller.getBoard()).toStrictEqual(afterMoveBoard);
  board.reset();
  expect(controller.getBoard()).toStrictEqual(defaultBoard);
});

test("should set board to victory ", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);

  expect(controller.getVictoryStatus()).toBe(false);
  controller.win();
  expect(controller.getVictoryStatus()).toBe(true);
});

test("should move a num to left", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const afterMoveBoard = [
    [1, 2, 3],
    [8, 4, 0],
    [7, 5, 6],
  ];

  controller.moveNum(4);
  expect(controller.getBoard()).toStrictEqual(afterMoveBoard);
});

test("should move a num to right", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const afterMoveBoard = [
    [1, 2, 3],
    [0, 8, 4],
    [7, 5, 6],
  ];

  controller.moveNum(8);
  expect(controller.getBoard()).toStrictEqual(afterMoveBoard);
});

test("should move a num to top", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const afterMoveBoard = [
    [1, 2, 3],
    [8, 5, 4],
    [7, 0, 6],
  ];

  controller.moveNum(5);
  expect(controller.getBoard()).toStrictEqual(afterMoveBoard);
});

test("should move a num to down", () => {
  const matrix = new MatrixImpl();
  const board = new BoardImpl(matrix);
  const controller = new GameController(board);
  const afterMoveBoard = [
    [1, 0, 3],
    [8, 2, 4],
    [7, 5, 6],
  ];

  controller.moveNum(2);
  expect(controller.getBoard()).toStrictEqual(afterMoveBoard);
});
