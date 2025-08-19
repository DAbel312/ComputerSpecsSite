<template>
    <div id="content">
        <h1 id="title">{{ data?.title }}</h1>
        <p id="author">Ein Artikel von {{  data?.author }}</p>
        <p id="date">Datum: {{ data?.date.slice(0, 10) }}</p>
        <p id="text">{{ data?.content }}</p>
    </div>
    <div id="moreNews">
        <RandomNews style="margin-bottom: 50px;"></RandomNews>
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

onMounted(async () => {
    try {
        const response = await axios.get<Article>('http://localhost:5174/api/article/getById/' + props.id);
        data.value = response.data;
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
    padding: 2% 2% 2% 2%;
    height: fit-content;
}

#title {
    color: var(--textGreen);
}

#author {

}

#date {
    margin-bottom: 20px;
}

#text {
    word-wrap: break-word;
    line-height: 20px;
    margin-bottom: 200px;
}
</style>