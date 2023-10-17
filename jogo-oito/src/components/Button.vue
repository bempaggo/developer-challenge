<script setup lang="ts">
defineProps<{ text: string, func: Function }>()
</script>

<template>
  <div class='btn' @click="func()">
    <h1>{{ text }}</h1>
  </div>
</template>

<style scoped>
.btn {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10%;
  background-color: #237847;
}

.btn:hover {
  cursor: pointer;
  border: 2px solid white;
}

h1 {
  filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, 1));
}
</style>
