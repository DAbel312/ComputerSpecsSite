<script setup>
import {onMounted} from "vue";
import axios from "axios";

onMounted(async () => {
  const response = await axios.get('http://localhost:5174/api/article/getAll');

  const allNewsDiv = document.getElementById('allNewsDiv');

  const pages = document.getElementById('pages');

  for (let i = 0; i < response.data.length; i++) {
    const newsDiv = document.createElement('div');
    allNewsDiv.appendChild(newsDiv);

    const newsDivTitle = document.createElement('H3');
    const newsDivContent = document.createElement('div');
    const newsDivDate = document.createElement('p');
    const newsDivAuthor = document.createElement('p');
    const newsDivCompleteButton = document.createElement('input');

    newsDivCompleteButton.type = "button";
    newsDivCompleteButton.value = "Weiter...";

    newsDivTitle.textContent = response.data[i].title;

    let contentNewsDiv = response.data[i].content.slice(0, 350) + "...";

    newsDivContent.textContent = contentNewsDiv;
    newsDivDate.textContent = ("Datum: " + response.data[i].date).slice(0, 17);
    newsDivAuthor.textContent = "Autor: " + response.data[i].author;

    newsDiv.appendChild(newsDivTitle);
    newsDiv.appendChild(newsDivContent);
    newsDiv.appendChild(newsDivCompleteButton);
    newsDiv.appendChild(newsDivDate);
    newsDiv.appendChild(newsDivAuthor);

    newsDiv.className = "newsDiv";
    newsDivTitle.className = "newsDivTitle";
    newsDivContent.className = "newsDivContent";
    newsDivDate.className = "newsDivDate"
    newsDivAuthor.className = "newsDivAuthor";
    newsDivCompleteButton.className = "completeNewsButton";
  }

  const numberPages = Math.ceil(response.data.length / 5);

  for (let j = 1; j <= numberPages; j++) {
    const pageSpan = document.createElement('span');

    pages.appendChild(pageSpan);
    pageSpan.innerHTML = j;

    pageSpan.onclick = function () {
      console.log(j);
    }
  }

})
</script>

<template>
  <div id="mainDiv">
    <h1>Neuigkeiten</h1>
    <div id="allNewsDiv"></div>
    <p id="pages"></p>
  </div>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  methods: {

  }
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