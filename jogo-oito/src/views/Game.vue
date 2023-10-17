<script setup lang="ts">
import Square from '../components/Square.vue'
import Button from '../components/Button.vue'
import { useGameStore } from '../stores/game'
import { computed } from 'vue'

const gameStore = useGameStore()
const gameBoard = gameStore.getBoard()
const gameBoardAsArray = computed(() => gameBoard.flat().flat())
const victoryStatus = computed(() => gameStore.checkVictoryStatus())
</script>

<template>
  <div class='game-container'>
    <div class='status-container'>
      <h1 v-if="victoryStatus == true">You win!</h1>
      <h1 v-else>Let's play!</h1>
    </div>
    <div class='board-container'>
      <Square v-for="num in gameBoardAsArray" :num="num" />
    </div>
    <div class='buttons-container'>
      <Button text="Reset" :func="gameStore.resetGame" />
      <Button text="Win" :func="gameStore.winGame" />
    </div>
  </div>
</template>

<style scoped>
.game-container {
  display: flex;
  height: 700px;
  flex-direction: column;
  justify-content: space-between;
  padding-bottom: 4%;
}

.status-container {
  height: 25px;
}

h1 {
  filter: drop-shadow(2px 2px 2px #237847);
}

.board-container {
  width: 500px;
  height: 500px;
  background-color: #237847;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 3%;
  padding: 3%;
  border-radius: 5%;
}

.buttons-container {
  width: 500px;
  height: 100px;
  display: flex;
  flex-direction: row;
  column-gap: 40%;
}
</style>
