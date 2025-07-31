<script setup>
import CsInput from "./CsInput.vue";
import CsButton from "./CsButton.vue";
</script>

<template>
  <div id="mainDiv">
    <h3>Willkommen im Admin-Dashboard</h3>
    <h4>Neuen Artikel erstellen</h4>
    <CsInput placeholder="Titel" width="40%" type="text" id="newsTitle" v-model="title" max-length="254"></CsInput>
    <textarea placeholder="Text" id="newsTextarea" v-model="content"></textarea>
    <CsButton content="Veröffentlichen" width="35%" id="newsPublish" @click="createArticle()"></CsButton>
    <p id="status" :class="{ 'text-red': isError, 'text-green': !isError }">{{ status }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      title: "",
      content: "",
      author: "",
      status: "",
      isError: ""
    }
  },
  methods: {
    async createArticle() {
      const token = localStorage.getItem('jwt');
      const payloadBase64 = token.split('.')[1];
      const payloadJson = atob(payloadBase64);
      const payload = JSON.parse(payloadJson);

      const authorToken = payload.sub;
      try {
        await axios.post('http://localhost:5174/api/article/create', {
          title: this.title,
          content: this.content,
          author: authorToken
        });

        this.isError = false;

        this.status = "Artikel erfolgreich veröffentlicht."

      } catch(error) {
        this.isError = true;
        this.status = "Veröffentlichung fehlgeschlagen. Bitte kontaktieren Sie einen Administrator."
      }
    }
  }
}

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
      padding: 15px 15px 15px 15px;
    }

    #newsTextarea:focus-within {
      border-radius: 1px;
      transition: border-radius 0.3s ease;
    }

    #newsPublish {
      margin-top: 15px;
    }
  }

  #status {
    margin-top: 20px;
  }

  .text-green {
    color: var(--primaryGreen1);
  }

  .text-red {
    color: red;
  }

</style>
