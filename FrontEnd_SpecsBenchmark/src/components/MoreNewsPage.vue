<template>
  <div id="mainDiv">
    <h1>Neuigkeiten</h1>
    <div id="allNewsDiv">
      <NewsComponent v-for="n in articlesShown" :key="n.id" :title="n.title" :content="n.content" :author="n.author" :date="n.date" :id="n.id"/>
    </div>
    <p id="pages">
      <span v-for="i in numPages" @click="switchPage(i)">
        {{ i }}
      </span>
    </p>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import {onMounted} from "vue";
import axios from "axios";
import NewsComponent from "./NewsComponent.vue"
import { Article } from "../domain/Article"

const articles = ref<Article[]>([]);
const numPages = ref<number>(0);
const articlesShown = ref<Article[]>([]);

/**
 * gets all articles when page is mounted
 * calculates number of pages
 */

onMounted(async () => {
  const response = await axios.get('http://localhost:5174/api/article/getAll');

  articles.value = response.data;

  const numberPages = Math.ceil(response.data.length / 5);

  numPages.value = numberPages;

  switchPage(1);
})

/**
 * switches pages, shows 5 articles
 * calculates, which articles to show on which page
 * @param page
 */

async function switchPage(page: number) {
  const secondSliceNumber = page * 5;
  const firstSliceNumber = secondSliceNumber - 5;

  articlesShown.value = articles.value.slice(firstSliceNumber, secondSliceNumber);
  
  window.scrollTo({
    top: 0,
    behavior: "smooth"
  });
}
</script>

<style scoped lang="scss">
  #mainDiv {
    padding: 1% 1% 1% 1%;
    box-sizing: border-box;
    min-height: 530px;
    background-color: var(--primaryDarkerBackgroundColor1);
  }

  h1 {
    text-align: center;
    color: var(--primaryBackgroundColor1);
  }

  #pages {
    text-align: center;
    font-size: 2rem;
    margin-top: 60px;
  }
</style>

<style>
  .newsDiv {
    border-radius: 20px;
    background-color: var(--primaryBackgroundColor1);
    width: 80%;
    height: fit-content;
    box-sizing: border-box;
    padding: 2% 2% 2% 2%;
    justify-self: center;
    box-shadow: var(--primaryGreen1) 0px 10px 20px, var(--primaryGreen1) 0px 6px 6px;
    margin-top: 60px;
  }

  .newsDivContent {
    word-break: break-word;
    line-height: 1.7;
    margin-top: 5px;
    margin-bottom: 20px;
  }

  .newsDivTitle {
    word-break: break-word;
    text-align: center;
    color: var(--textGreen);
    font-size: 1.7rem;
  }

  .newsDivDate, .newsDivAuthor {
    color: var(--primaryColor2);
    font-size: 0.8rem;
  }

  #pages span {
    margin-left: 10px;
  }

  #pages span:hover {
    cursor: pointer;
    font-weight: bold;
    color: var(--primaryGreen1);
  }
</style>