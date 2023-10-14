<script setup lang="ts">
import { useGameStore } from '../stores/game';
import IMAGES from '../assets/Images.ts';

defineProps<{ num: number }>()
const store = useGameStore()
</script>

<template>
  <div class='btn' @click="store.move(num)" :style="{ backgroundImage: `url(${IMAGES[num]})` }">
    <h1>{{ num }}</h1>
  </div>
</template>

<style scoped>
.btn {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: start;
  align-items: end;
  padding-left: 5%;
  border-radius: 10%;
  background-size: cover;
  background-position: center;
}

h1 {
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 1));
}
</style>
