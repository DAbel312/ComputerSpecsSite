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
import { nextTick, ref } from "vue";
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

  nextTick(() => {
    switchPage(1);
  })
})

/**
 * switches pages, shows 5 articles
 * calculates, which articles to show on which page
 * changes color of page span, which is selected at the moment
 * @param page
 */

async function switchPage(page: number) {
  const spans = Array.from(document.querySelectorAll<HTMLSpanElement>('span'));

  spans.forEach(span => {
    span.style.fontWeight = 'normal';
  })

  const spansWithOne = spans.filter(span =>
    span.textContent?.trim() === page.toString()
  );

  spansWithOne[0].style.fontWeight = 'bold';

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

  #allNewsDiv {
    min-height: 1200px;
  }

  #pages {
    text-align: center;
    font-size: 2rem;
    margin-top: 60px;
  }
</style>

<style>
  #pages span {
    margin-left: 10px;
  }

  #pages span:hover {
    cursor: pointer;
    font-weight: bold !important;
  }
</style>