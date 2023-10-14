<script setup lang="ts">
import Square from '../components/Square.vue'
import { useGameStore } from '../stores/game'
const store = useGameStore()
const board = store.game.getBoardMatrix()
</script>

<template>
  <div class='container'>
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
</template>

<style scoped>
.container {
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
</style>
