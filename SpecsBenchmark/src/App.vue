<script setup>
// import HelloWorld from './components/HelloWorld.vue'
import HeaderComponent from './components/MainHeader.vue';
import footerComponent from './components/MainFooter.vue';
</script>

<template>
  <nav>
    <HeaderComponent/>
  </nav>
  <main>
    <router-view/>
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
