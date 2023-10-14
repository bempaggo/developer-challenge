<script setup lang="ts">
import Button from '../components/Button.vue'
import { useGameStore } from '../stores/game'
const store = useGameStore()
const board = store.game.getBoardMatrix()
</script>

<template>
  <div class='container'>
    <Button :num="board[0][0]" />
    <Button :num="board[0][1]" />
    <Button :num="board[0][2]" />
    <Button :num="board[1][0]" />
    <Button :num="board[1][1]" />
    <Button :num="board[1][2]" />
    <Button :num="board[2][0]" />
    <Button :num="board[2][1]" />
    <Button :num="board[2][2]" />
  </div>
</template>

<style scoped>
.container {
  width: 300px;
  height: 300px;
  background-color: red;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  gap: 3%;
  padding: 3%;
  border-radius: 5%;
}
</style>
