<script setup>

</script>

<template>
  <div id="mainArea">
    <div id="gpuMain" class="mainAreaHome">
      <div id="gpuMain1"></div>
      <div id="gpuMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <a href="#" class="linkMainArea" >GPU</a>
      </div>
    </div>
    <div id="cpuMain" class="mainAreaHome">
      <div id="cpuMain1"></div>
      <div id="cpuMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <a href="#" class="linkMainArea">CPU</a>
      </div>
    </div>
    <div id="ssdMain" class="mainAreaHome">
      <div id="ssdMain1"></div>
      <div id="ssdMain2">
        <span>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</span>
        <a href="#" class="linkMainArea">SSD</a>
      </div>
    </div>
  </div>
  <div id="newsArea">
    <h2 id="h2NewsArea">Neuigkeiten</h2>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style scoped lang="scss">
* {

}

#mainArea {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: repeat(3, 1fr);
  place-items: center;
  background-color: var(--primaryBackgroundColor1);
}

.mainAreaHome {
  width: 350px;
  height: 470px;
  border-radius: 13px;
  box-shadow: var(--primaryGreen1) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;
  margin-top: 20%;
  margin-bottom: 20%;
  box-sizing: border-box;
  scale: 1.3;
  display: flex;
  flex-direction: column;
  justify-content: space-between;


  #gpuMain1, #cpuMain1, #ssdMain1 {
    width: 100%;
    height: 45%;
    background-color: black;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
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

  a {
    color: var(--black);
    font-weight: bold;
    text-decoration-line: none;
    background-color: var(--primaryGreen1);
    display: block;
    width: 60%;
    height: 40px;
    border-radius: 7px;
    text-align: center;
    padding-top: 3%;
    box-sizing: border-box;
    margin-left: auto;
    margin-right: auto;
  }

  a:hover {
    background-color: var(--greenHover);
    transition: background-color 0.3s;
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

#newsArea{
  width: 100%;
  height: 600px;
  background-color: var(--primaryColor2);
  box-sizing: border-box;

  #h2NewsArea {
    text-align: center;
    color: var(--primaryBackgroundColor1);
    font-weight: bold;
    font-size: 3rem;
    padding-top: 20px;
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



































