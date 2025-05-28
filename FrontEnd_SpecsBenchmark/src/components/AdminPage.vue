<script setup>
import CsInput from "./CsInput.vue";
import CsButton from "./CsButton.vue";
</script>

<template>
  <div id="mainDiv">
    <h3>Willkommen im Admin-Dashboard</h3>
    <h4>Neuen Artikel erstellen</h4>
    <CsInput placeholder="Titel" width="40%" type="text" id="newsTitle"></CsInput>
    <textarea placeholder="Text" id="newsTextarea"></textarea>
    <CsButton content="Veröffentlichen" width="35%" id="newsPublish"></CsButton>
  </div>
</template>

<script>

</script>

<style scoped lang="scss">
  #mainDiv {
    background-color: var(--primaryBackgroundColor1);
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 1fr 1fr 4fr 1fr;
    padding: 2% 2% 2% 2%;
    box-sizing: border-box;

    h4 {
      margin-top: 5px;
    }

    #newsTitle {
      margin-top: 5px;
    }

    #newsTextarea {
      margin-top: 15px;
      resize: none;
      outline: none;
      border-radius: 30px;
      transition: border-radius 0.3s ease;
      border: none;
      box-sizing: border-box;
      padding: 2% 2% 2% 2%;
    }

    #newsTextarea:focus-within {
      border-radius: 1px;
      transition: border-radius 0.3s ease;
    }

    #newsPublish {
      margin-top: 15px;
    }
  }

</style>