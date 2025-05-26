<template>
    <div id="newsCard">
        <h1>{{ title }}</h1>
        <div id="newsCard">
            {{ content }}
        </div>
    </div>
</template>

<script setup lang="ts">
const props = withDefaults(defineProps<{
  title: string,
  content: string
}>(),{
  title: "NewsCard",
  content: "News"
})
</script>

<style lang="scss">

#newsCard {
    width: 60%;
    height: 300px;
    background-color: pink;
}

</style>