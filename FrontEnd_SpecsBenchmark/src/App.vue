<script setup>
import HeaderComponent from './components/MainHeader.vue';
import footerComponent from './components/MainFooter.vue';
</script>

<template>
  <nav>
    <HeaderComponent/>
  </nav>
  <main>
    <router-view :key="$route.fullPath"/>
  </main>
  <footer>
    <footerComponent/>
  </footer>
</template>

<style scoped>
main {
  min-height: 80%;
  width: 100%;
}
</style>

<script>

</script>
