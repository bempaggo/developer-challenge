<script setup lang="ts">
import { useGameStore } from '../stores/game'

defineProps<{ num: number }>()
const store = useGameStore()
</script>

<template>
  <div class='btn' @click="store.move(num)">
    <h1>{{ num }}</h1>
  </div>
</template>

<style scoped>
.btn {
  width: 100%;
  height: 100%;
  background-color: blue;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10%;
}
</style>
