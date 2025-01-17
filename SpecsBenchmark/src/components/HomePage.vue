<script setup>

</script>

<template>
  <div id="mainArea">
    <div id="gpuMain" class="mainAreaHome">
      <div id="gpuMain1"></div>
      <div id="gpuMain2">
        <a href="#">GPU</a>
      </div>
    </div>
    <div id="cpuMain" class="mainAreaHome">
      <div></div>
      <div>
        <a href="#">CPU</a>
      </div>
    </div>
    <div id="ssdMain" class="mainAreaHome">
      <div></div>
      <div>
        <a href="#">SSD</a>
      </div>
    </div>
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
}

.mainAreaHome {
  width: 350px;
  height: 470px;
  border-radius: 13px;
  box-shadow: var(--primaryGreen1) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;
  margin-top: 20%;
  margin-bottom: 20%;
  box-sizing: border-box;

  :first-child {
    width: 100%;
    height: 35%;
    background-color: black;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
  }

  :nth-child(2) {
    width: 100%;
    height: 65%;
    background-color: var(--primaryBackgroundColor1);
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
  }

  a {
    color: var(--black);
    font-weight: bold;
    text-decoration-line: none;
    background-color: var(--primaryGreen1) !important;
    display: block;
    width: 60% !important;
    height: 40px !important;
    border-radius: 7px !important;
    text-align: center;
    padding-top: 3%;
    box-sizing: border-box;
    margin-left: auto;
    margin-right: auto;
  }
}

#gpuMain:hover, #cpuMain:hover, #ssdMain:hover {
  scale: 1.05;
  transition: scale 0.3s;
}

</style>



































