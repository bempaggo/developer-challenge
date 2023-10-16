import { GameController } from "../controller/GameController";
import { BoardImpl } from "../model/BoardImpl";
import { MatrixImpl } from "../model/MatrixImpl";

export class GameContainer {
  private controller: GameController;

  constructor() {
    const matrix = new MatrixImpl();
    const board = new BoardImpl(matrix);
    this.controller = new GameController(board);
  }

  get() {
    return this.controller;
  }
}
