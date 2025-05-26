<script setup>
import CsButton from "./CsButton.vue";
import router from "../router/index.js";
</script>

<template>
  <div id="mainArea">
    <div id="gpuMain" class="mainAreaHome">
      <div id="gpuMain1">
        <img src="../assets/Bilder/LogoGrafic.webp" alt="GPU">
      </div>
      <div id="gpuMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <csButton @click="router.go(-1)" content="GPU" class="mainAreaButton"/>
      </div>
    </div>
    <div id="cpuMain" class="mainAreaHome">
      <div id="cpuMain1">
        <img src="../assets/Bilder/LogoCPU.webp" alt="CPU">
      </div>
      <div id="cpuMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <csButton @click="router.go(-1)" content="CPU" class="mainAreaButton"/>
      </div>
    </div>
    <div id="ssdMain" class="mainAreaHome">
      <div id="ssdMain1">
        <img src="../assets/Bilder/LogoSSD.webp" alt="CPU">
      </div>
      <div id="ssdMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <csButton @click="router.go(-1)" content="SSD" class="mainAreaButton"/>
      </div>
    </div>
  </div>
  <div id="newsArea">
    <h2 id="h2NewsArea">Neuigkeiten</h2>
    <csButton @click="" content="Mehr Neuigkeiten" width="200px" class="mainAreaButton" id="moreNewsButton"/>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data() {
    return {
      isCreateNewsVisible: false,
    };
  },
  methods: {
    }
};
</script>

<style scoped lang="scss">

#mainArea {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: repeat(3, 1fr);
  place-items: center;
}

.mainAreaHome {
  width: 350px;
  height: 470px;
  border-radius: 13px;
  box-shadow: var(--primaryGreen1) 0px 10px 20px, var(--primaryGreen1) 0px 6px 6px;
  margin-top: 20%;
  margin-bottom: 20%;
  box-sizing: border-box;
  scale: 1.3;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  transition: scale 0.3s ease-in-out;


  #gpuMain1, #cpuMain1, #ssdMain1 {
    width: 100%;
    height: 45%;
    background-color: black;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;

    img {
      width: 100%;
      height: 155%;
      border-top-left-radius: 12px;
      border-top-right-radius: 12px;
    }
  }

  #gpuMain2, #cpuMain2, #ssdMain2 {
    box-sizing: border-box;
    width: 100%;
    height: 55%;
    background-color: var(--primaryBackgroundColor1);
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
    justify-content: flex-end;
    align-items: center;
  }

  .mainAreaButton {
    font-weight: bold;
    text-decoration-line: none;
    display: block;
    width: 60%;
    height: 40px;
    text-align: center;
    padding-top: 3%;
    box-sizing: border-box;
    margin-left: auto;
    margin-right: auto;
    margin-top: 5%;
  }


  span {
    margin: 10px 10px 10px 10px;
    display: block;
    height: 67%;
    word-wrap: break-word;
    line-height: 30px;
    text-align: center;
  }
}

.linkMainArea {
  margin-top: 20px;
}

#gpuMain:hover, #cpuMain:hover, #ssdMain:hover {
  scale: 1.35;
  transition: scale 0.3s;
}

#newsArea {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 5fr 1fr;
  width: 100%;
  height: 900px;
  background-color: var(--primaryColor2);
  box-sizing: border-box;

  #h2NewsArea {
    text-align: center;
    color: var(--primaryBackgroundColor1);
    font-weight: bold;
    font-size: 3rem;
    padding-top: 20px;
    grid-row: 1;
    grid-column: 1;
  }

  #moreNewsButton {
    grid-row: 3;
    grid-column: 1;
    justify-self: center;
    height: 40px;
  }
}

@media (max-width: 1430px) {
  #mainArea {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(3, 1fr);
  }

  .mainAreaHome {
    scale: 1.6;
  }

  #gpuMain:hover, #cpuMain:hover, #ssdMain:hover {
    scale: 1.65;
    transition: scale 0.3s;
  }
}

@media (max-width: 910px) {
  .mainAreaHome {
    scale: 1.3;
  }

  #gpuMain:hover, #cpuMain:hover, #ssdMain:hover {
    scale: 1.35;
    transition: scale 0.3s;
  }
}

@media (max-width: 500px) {
  .mainAreaHome {
    scale: 0.9;
  }

  #gpuMain:hover, #cpuMain:hover, #ssdMain:hover {
    scale: 0.95;
    transition: scale 0.3s;
  }
}

</style>



































