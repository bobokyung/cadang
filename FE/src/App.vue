<template>
  <div class="app">
    <TheHeader />
    <div class="main-content">
      <RouterView />
    </div>
  </div>
  <TheFooter v-if="isFooter" class="TheFooter" />
</template>

<script setup>
import TheFooter from './components/common/TheFooter.vue';
import TheHeader from './components/common/TheHeader.vue';
import router from '@/router';
import { RouterLink, RouterView } from 'vue-router'
import { userAccessToken, isFooter } from "@/stores/util"
</script>

<style scoped>
.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  position: relative;

  z-index: 1;
  padding-bottom: 5%;
  margin-bottom: 5%;
}

.main-content {
  flex: 1;
  /* Adjust margin bottom to avoid overlapping with footer */
}

.TheFooter {
  position: fixed;
  /* Absolute positioning for the footer */
  bottom: 0;
  /* Place the footer at the bottom of the parent container */
  width: 100%;
  /* Occupy the full width of the parent container */
}
</style>
