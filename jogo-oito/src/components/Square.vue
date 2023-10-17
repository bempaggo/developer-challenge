<script setup lang="ts">
import { useGameStore } from '../stores/game'
import IMAGES from '../assets/Images.ts'
import { computed } from 'vue';

defineProps<{ num: number }>()
const store = useGameStore()
const victoryStatus = computed(() => store.checkVictoryStatus())
</script>

<template>
  <div v-bind:class='{ btn: num != 0 || victoryStatus, btnZero: num == 0 && !victoryStatus }' @click="store.moveNum(num)"
       v-bind:style="[ num != 0 || victoryStatus ? { backgroundImage: `url(${IMAGES[num]})` } : { backgroundImage: none }]">
  </div>
</template>

<style scoped>
.btn,
.btnZero {
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

.btn:hover {
  border: 2px solid rgba(255, 255, 255, 0.8);
  cursor: pointer;
}

.btnZero {
  border: 2px solid rgba(255, 255, 255, 0.5);
}

h1 {
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 1));
}
</style>
