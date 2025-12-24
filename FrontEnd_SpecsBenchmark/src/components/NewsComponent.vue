<template>
    <div id="newsCard">
        <h3>{{ title }}</h3>
        <div id="newsContent">
            {{ content.slice(0, 1500) + "..." }}
        </div>
        <div id="buttonDiv">
            <CsButton content="Weiter..." :paddingTopBottom="5" width="130px" id="continueButton" @click="props.id != null ? goToFullArticle(props.id) : null"></CsButton>
        </div>
        <p>{{ date.slice(0, 10) }}</p>
        <p>{{ author }}</p>
    </div>
</template>

<script setup lang="ts">
import CsButton from "./CsButton.vue";
import router from "../router/index.js";

const props = withDefaults(defineProps<{
  title: string,
  content: string,
  author: string,
  date: string,
  id: number
}>(),{
  title: "NewsCard",
  content: "News",
  author: "Author",
  date: "date",
})

function goToFullArticle (id: number) {
  try {
    router.push({ name: 'article', params: { id: id } })
  } catch (err) {
    console.error("Die Seite konnte nicht geladen werden." + err)
  }
}
</script>

<style lang="scss">

#newsCard{
  margin-top: 40px;
  border-radius: 12px;
  background-color: var(--primaryBackgroundColor1);
  width: 90%;
  height: fit-content;
  box-sizing: border-box;
  margin-left: 5%;
  padding: 2% 2% 2% 2%;
  box-shadow: var(--primaryGreen1) 0px 10px 20px, var(--primaryGreen1) 0px 6px 6px;

  h3 {
    color: var(--textGreen);
    word-break: break-word;
    font-size: 1.7rem;
    text-align: center;
  }

  #newsContent {
    word-break: break-word;
    margin-top: 5px;
    margin-bottom: 20px;
    line-height: 1.7;
  }

  #continueButton {
    margin-top: 20px;
    margin-bottom: 10px;
  }

  p {
    color: var(--primaryColor2);
    font-size: 0.8rem;
  }

  #buttonDiv {
    display: grid;
    justify-content: center;
  }
}

</style>