import { defineStore } from "pinia";
import GameController from "../../controllers/GameController";

export const useGameStore = defineStore("game", {
  state: () => ({
    game: new GameController(),
  }),
  getters: {},
  actions: {
    getBoard() {
      return this.game.getBoard()
    },

    moveNum(num: number) {
      if (!this.game.getVictoryStatus()) this.game.moveNum(num);
    },

    resetGame() {
      this.game.resetGame();
    },

    winGame() {
      this.game.winGame();
    },
  },
});
