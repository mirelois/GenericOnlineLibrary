<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
  </header>

  <RouterView />
</template>
<style>
@layer primevue {
  .p-rating .p-rating-item.p-rating-item-active .p-rating-icon {
    color: #EAE600;
  }
}
</style>