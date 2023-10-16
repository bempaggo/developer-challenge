<script setup lang="ts">
import Square from '../components/Square.vue'
import Button from '../components/Button.vue'
import { useGameStore } from '../stores/game'
const store = useGameStore()
const board = store.getBoard()
</script>

<template>
  <div class='game-container'>
    <div class='board-container'>
      <Square :num="board[0][0]" />
      <Square :num="board[0][1]" />
      <Square :num="board[0][2]" />
      <Square :num="board[1][0]" />
      <Square :num="board[1][1]" />
      <Square :num="board[1][2]" />
      <Square :num="board[2][0]" />
      <Square :num="board[2][1]" />
      <Square :num="board[2][2]" />
    </div>
    <div class='buttons-container'>
      <Button text="Reset" :func="store.resetGame" />
      <Button text="Win" :func="store.winGame" />
    </div>
  </div>
</template>

<style scoped>
.game-container {
  display: flex;
  height: 620px;
  flex-direction: column;
  justify-content: space-between;
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
