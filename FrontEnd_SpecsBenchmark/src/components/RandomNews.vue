<template>
    <h2>Weitere Neuigkeiten</h2>
    <div id="moreNews" v-if="articles.length == 3">
        <div class="moreNewsContainer">
            <h3>{{ articles[0].title }}</h3>
            <p>{{ articles[0].content.slice(0, 200) + "..." }}</p>
            <div id="buttonDiv">
                <CsButton content="Weiter..." :paddingTopBottom="5" width="130px" id="continueButton" @click="articles[1].id != null ? goToFullArticle(articles[1].id) : null"></CsButton>
            </div>
        </div>
        <div class="moreNewsContainer">
            <h3>{{ articles[1].title }}</h3>
            <p>{{ articles[1].content.slice(0, 200) + "..." }}</p>
            <div id="buttonDiv">
                <CsButton content="Weiter..." :paddingTopBottom="5" width="130px" id="continueButton" @click="articles[1].id != null ? goToFullArticle(articles[1].id) : null"></CsButton>
            </div>
        </div>
        <div class="moreNewsContainer">
            <h3>{{ articles[2].title }}</h3>
            <p>{{ articles[2].content.slice(0, 200) + "..." }}</p>
            <div id="buttonDiv">
                <CsButton content="Weiter..." :paddingTopBottom="5" width="130px" id="continueButton" @click="articles[1].id != null ? goToFullArticle(articles[1].id) : null"></CsButton>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import type { Article } from "../domain/Article";
import { onMounted, ref, watch } from 'vue';
import axios from "axios";
import { useRouter } from "vue-router";
import CsButton from "./CsButton.vue";

const router = useRouter();

const articles = ref<Article[]>([]);

onMounted(async () => {
    try {
        const { data } = await axios.get<Article[]>('http://localhost:5174/api/article/getRandom');
        articles.value = data;
    } catch (err) {
        console.error("Random articles could not be loaded. Error: " + err);
    }
})

function goToFullArticle(id: number) {
  router.push({ name: "article", params: { id: String(id) } });
}
</script>

<style scoped lang="scss">

* {
    background-color: var(--primaryColor2);
}

h2 {
    text-align: center;
    color: white;
    padding-top: 20px;
    padding-bottom: 20px;
}

#moreNews {
    display: grid;
    grid-template-columns: 33.3% 33.3% 33.3%;
    grid-template-rows: 1fr;
    box-sizing: border-box;
    padding-bottom: 50px;
}

.moreNewsContainer {
    display: grid;
    grid-template-rows: 10% 80% 10%;
    grid-template-columns: 100%;
    height: 350px;
    width: 80%;
    justify-self: center;
    word-wrap:break-word;
    box-sizing: border-box;
    background-color: white;
    padding: 3% 3% 3% 3%;
    background-color: var(--primaryBackgroundColor1);
    border-radius: 12px;
    box-shadow: var(--primaryGreen1) 0px 10px 20px, var(--primaryGreen1) 0px 6px 6px;

    p, h3 {
        background-color: var(--primaryBackgroundColor1);
    }

    p {
        line-height: 20px;
        margin-top: 15px;
    }

    h3 {
        text-align: center;
    }
}

#buttonDiv {
    background-color: var(--primaryBackgroundColor1);
    justify-self: center;
}

@media (max-width: 800px) {
    #moreNews {
        grid-template-columns: 100%;
        grid-template-rows: auto auto auto;
    }

    .moreNewsContainer {
        margin-top: 30px;
    }
}
</style>