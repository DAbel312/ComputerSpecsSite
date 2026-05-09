<template>
  <div id="mainArea">
    <div id="gpuMain" class="mainAreaHome">
      <div id="gpuMain1">
        <img src="../assets/Bilder/LogoGrafic.webp" alt="GPU">
      </div>
      <div id="gpuMain2">
        <span>Hier kannst Du die Spezifikationen von GPUs vergleichen, wie z. B. die Shader-Einheiten, Tensor Cores oder ROPs. Darunter fallen sowohl GPUs von Intel, AMD sowie Nvidia.</span>
        <csButton @click="router.push('allGpus')" content="Alle GPUs" class="mainAreaButton"/>
        <csButton @click="router.push('gpu/gpuName')" content="GPU-Vergleich" class="mainAreaButton"/>
      </div>
    </div>
    <div id="cpuMain" class="mainAreaHome">
      <div id="cpuMain1">
        <img src="../assets/Bilder/LogoCPU.webp" alt="CPU">
      </div>
      <div id="cpuMain2">
        <span>Hier kannst Du CPUs von AMD und Intel vergleichen. Hier siehst Du dann z. B. die unterschiedlichen Kerne, Taktraten oder Cache.</span>
        <csButton @click="router.push('allGpus')" content="Alle CPUs" class="mainAreaButton"/>
        <csButton @click="router.go(-1)" content="CPU-Vergleich" class="mainAreaButton"/>
      </div>
    </div>
    <div id="ssdMain" class="mainAreaHome">
      <div id="ssdMain1">
        <img src="../assets/Bilder/LogoSSD.webp" alt="CPU">
      </div>
      <div id="ssdMain2">
        <span>Hier kannst Du unterschiedliche SSDs vergleichen. Hier siehst Du dann z. B. Lese- und Schreibgeschwindigkeiten sowie die Geschwindigkeiten von Random Operationen.</span>
        <csButton @click="router.push('allGpus')" content="Alle SSDs" class="mainAreaButton"/>
        <csButton @click="router.go(-1)" content="SSD-Vergleich" class="mainAreaButton"/>
      </div>
    </div>
  </div>
  <div id="gameArea">
    <h2>GPU Idle Tycoon</h2>
    <span>Hier kannst Du ein GPU Idle Tycoon spielen, bei dem Du GPUs verkaufen kannst, die Nachfrage steigern kannst und in die Produktion investieren kannst. Der Fortschritt wird im Browser gespeichert.</span>
    <csButton @click="router.push('gpuIdleTycoon')" href="/news" content="GPU Idle Tycoon" width="200px" class="mainAreaButton" id="gameButton"/>
  </div>
  <div id="newsArea">
    <h2 id="h2NewsArea">Neuigkeiten</h2>
    <div id="newsComponents" v-for="(article, idx) in articles" :key="article.id ?? idx">
      <NewsComponent :author="article.author" :title="article.title" :content="article.content" :date="article.date" :id="article.id"/>
    </div>
    <csButton @click="goToNewsPage()" href="/news" content="Mehr Neuigkeiten" width="200px" class="mainAreaButton" id="moreNewsButton"/>
  </div>
</template>

<script setup>
import CsButton from "./CsButton.vue";
import router from "../router/index.js";
import NewsComponent from "./NewsComponent.vue";
import {onMounted} from "vue";
import axios from "axios";
import { ref } from 'vue';

const articles = ref([])

defineOptions({ name: 'HomePage' })        

onMounted(async () => {
  try {
    const { data } = await axios.get('http://localhost:5174/api/article/get');

    articles.value = Array.isArray(data) ? data : [];
  } catch (err) {
    console.error("Fehler beim Laden der Artikel " + err);
  }
  })

  const goToNewsPage = ()  => router.push('news');
</script>

<style scoped lang="scss">
@mixin card-shadow {
  box-shadow: rgba(100, 100, 111, 0.2) 0 7px 29px 0;
}

@mixin rounded-top($radius: 12px) {
  border-top-left-radius: $radius;
  border-top-right-radius: $radius;
}

@mixin rounded-bottom($radius: 12px) {
  border-bottom-left-radius: $radius;
  border-bottom-right-radius: $radius;
}

@mixin centered-text {
  word-wrap: break-word;
  line-height: 30px;
  text-align: center;
}

main {
  background-color: var(--white);
}

#mainArea {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  place-items: center;
  background-color: var(--white);
}

.mainAreaHome {
  width: 350px;
  height: 470px;
  margin: 20% 0;
  border-radius: 13px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transform: scale(1.3);
  transition: transform 0.3s ease-in-out;

  @include card-shadow;

  &:hover {
    transform: scale(1.35);
  }

  #gpuMain1,
  #cpuMain1,
  #ssdMain1 {
    width: 100%;
    height: 45%;
    background-color: black;

    @include rounded-top;

    img {
      width: 99.8%;
      height: 155%;
      padding-left: 0.1%;

      @include rounded-top;
    }
  }

  #gpuMain2,
  #cpuMain2,
  #ssdMain2 {
    width: 100%;
    height: 55%;
    box-sizing: border-box;
    background-color: var(--primaryBackgroundColor1);
    justify-content: flex-end;
    align-items: center;

    @include rounded-bottom;
  }

  span {
    display: block;
    height: 45%;
    margin: 10px;

    @include centered-text;
  }

  .mainAreaButton {
    display: block;
    width: 60%;
    height: 40px;
    margin: 5% auto 0;
    padding-top: 3%;
    box-sizing: border-box;
    font-weight: bold;
    text-align: center;
    text-decoration-line: none;
  }
}

.linkMainArea {
  margin-top: 20px;
}

#gameArea,
#newsArea {
  width: 90%;
  height: fit-content;
  box-sizing: border-box;
  justify-self: center;
  border-radius: 5px;
  background-color: var(--primaryBackgroundColor1);
  margin-bottom: 40px;

  @include card-shadow;
}

#gameArea {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr;
  padding-bottom: 20px;
  transition: transform 0.3s ease-in-out;

  &:hover {
    transform: scale(1.02);
  }

  h2 {
    padding-top: 20px;
    font-size: 3rem;
    text-align: center;
    word-break: break-word;
  }

  span {
    margin: 20px 10px 10px;

    @include centered-text;
  }

  #gameButton {
    height: 40px;
    margin-top: 30px;
    justify-self: center;
  }
}

#newsArea {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: auto auto auto auto;

  #h2NewsArea {
    grid-row: 1;
    grid-column: 1;
    margin-bottom: 30px;
    padding-top: 20px;
    font-size: 3rem;
    font-weight: bold;
    text-align: center;
    word-break: break-word;
  }

  #moreNewsButton {
    grid-row: 5;
    grid-column: 1;
    height: 40px;
    margin: 50px;
    justify-self: center;
  }
}

.newsDiv0,
.newsDiv1 {
  width: 80%;
  height: fit-content;
  box-sizing: border-box;
  padding: 2%;
  border-radius: 20px;

  @include card-shadow;

  h3 {
    color: var(--textGreen);
    font-size: 1.7rem;
    word-break: break-word;
  }

  div {
    margin-top: 5px;
    margin-bottom: 20px;
    line-height: 1.7;
    word-break: break-word;
  }

  p {
    color: var(--primaryColor2);
    font-size: 0.8rem;
  }
}

.newsDiv0 {
  margin-left: 3%;
  background-color: var(--primaryBackgroundColor1);
}

.newsDiv1 {
  margin-top: 60px;
  margin-right: 3%;
  margin-left: 17%;
  background-color: var(--white);
}

@media (max-width: 1439px) {
  #mainArea {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(3, 1fr);
  }

  .mainAreaHome {
    transform: scale(1.6);

    &:hover {
      transform: scale(1.65);
    }
  }
}

@media (max-width: 910px) {
  .mainAreaHome {
    transform: scale(1.3);

    &:hover {
      transform: scale(1.35);
    }
  }
}

@media (max-width: 500px) {
  .mainAreaHome {
    transform: scale(0.9);

    &:hover {
      transform: scale(0.95);
    }
  }
}
</style>