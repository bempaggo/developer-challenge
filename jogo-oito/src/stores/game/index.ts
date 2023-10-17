import { defineStore } from "pinia";
import { GameContainer } from "../../di/GameContainer";

export const useGameStore = defineStore("game", {
  state: () => ({
    game: new GameContainer().get(),
  }),
  getters: {},
  actions: {
    getBoard() {
      return this.game.getBoard();
    },

    moveNum(num: number) {
      if (!this.game.getVictoryStatus()) this.game.moveNum(num);
    },

    resetGame() {
      this.game.reset();
    },

    winGame() {
      this.game.win();
    },

    checkVictoryStatus() {
      return this.game.getVictoryStatus();
    },
  },
});
