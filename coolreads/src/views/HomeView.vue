<script setup>
import NavComponent from '../components/NavComponent.vue';
import ShelfSideBarComponent from '../components/ShelfSideBarComponent.vue';
import FooterComponent from '../components/FooterComponent.vue';
</script>
<template>
  <main>
    <NavComponent></NavComponent>
    <ShelfSideBarComponent></ShelfSideBarComponent>
  </main>
</template>
<style>
@media (min-width: 1024px) {
    #app {
        padding: 0;
    }
}
#app {
    max-width: 3000px;
    font-weight: normal;
}
.newfooter {
    margin-top: 0px;
    margin-left: -1400px;
}
</style>