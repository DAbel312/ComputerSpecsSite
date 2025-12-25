<template>
    <div id="content">
        <h1 id="title">{{ data?.title }}</h1>
        <p id="author">Ein Artikel von {{  data?.author }}</p>
        <p id="date">Datum: {{ data?.date.slice(0, 10) }}</p>
        <img :src="`http://localhost:5174/api/image/get/${imageId}`" alt="Artikel Bild" id="picture">
        <pre id="text">{{ data?.content }}</pre>
    </div>
    <div id="moreNews">
        <RandomNews></RandomNews>
    </div>
</template>

<script setup lang="ts">
import type { Article } from "../domain/Article";
import { onMounted, ref } from 'vue';
import axios from "axios";
import router from "../router/index.js";
import RandomNews from "./RandomNews.vue";

const props = withDefaults(defineProps<{
  id: string
}>(),{
})

const data = ref<Article | null>(null);

const imageId = ref()

onMounted(async () => {
    try {
        const response = await axios.get<Article>('http://localhost:5174/api/article/getById/' + props.id);
        data.value = response.data;

        imageId.value = response.data.imageId;

    } catch (err) {
        console.error("The article with the id " + props.id + " could not be loaded. Error: " + err);
        router.push('/notFound');
    }
})
</script>

<style scoped>

* {
    background-color: var(--primaryBackgroundColor1);
}

#content {
    box-sizing: border-box;
    padding: 25px 25px 25px 25px;
    height: fit-content;
    max-width: 100%;
    overflow: auto;
}

#title {
    color: var(--textGreen);
    word-wrap: break-word;
}

#date {
    margin-bottom: 20px;
}

#text {
    word-wrap: break-word;
    line-height: 20px;
    margin-bottom: 200px;
    white-space: pre-wrap;
    max-width: 100%;
}

img {
    width: 50%;
    float: right;
}

@media (max-width: 400px) {
    p, h1, pre {
        text-align: center;
    }
}
</style>