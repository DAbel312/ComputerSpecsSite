<template>
  <div id="mainDiv">
    <h3>Willkommen im Admin-Dashboard</h3>
    <h4>Neuen Artikel erstellen</h4>
    <CsInput placeholder="Titel" width="40%" type="text" id="newsTitle" v-model="title" max-length="254"></CsInput>
    <textarea placeholder="Text" id="newsTextarea" v-model="content"></textarea>
    <p id="status" :class="{ 'text-red': isError, 'text-green': !isError }">{{ status }}</p>
    <div>
      <h4>Bild hochladen (.png)</h4>
        <form @submit.prevent="submit">
        <input type="file" accept="image/png" @change="onFile" />
        <p v-if="error" id="statusImageError">Fehler beim Hochladen des Bildes</p>
        <pre v-if="response" id="statusImage">Bild wurde erfolgreich hochgeladen.</pre>
      </form>
    </div>
    <CsButton content="Veröffentlichen" width="35%" id="newsPublish" @click="createArticle(); createArticleWithImage()"></CsButton>
  </div>
</template>

<script setup>
import CsInput from "./CsInput.vue";
import CsButton from "./CsButton.vue";
import { ref } from 'vue';
import axios from 'axios';

const file = ref(null)
const loading = ref(false)
const error = ref('')
const response = ref('')

function onFile(e) {
  const f = e.target.files?.[0]
  if (!f) {
    file.value = null;
    return
  }
  if (f.type !== 'image/png') {
    error.value = "Bitte eine PNG-Datei wählen."
    file.value = null
    return
  }
  file.value = f;
}

async function submit() {
  if (!file.value) return
  loading.value = true
  error.value = ''
  try {
    const fd = new FormData()
    fd.append('image', file.value, file.value.name)

    const res = await axios.post('http://localhost:5174/api/image/post', fd)

    response.value = JSON.stringify(res.data, null, 2)
  } catch (e) {
    error.value = e?.response?.data?.message || e.message
  } finally {
    loading.value = false
  }
}

async function createArticleWithImage() {
  if (!file.value) return
  loading.value = true
  error.value = ''
  try {
    const fd = new FormData()
    fd.append('image', file.value, file.value.name)

    const res = await axios.post('http://localhost:5174/api/image/post', fd)

    response.value = JSON.stringify(res.data, null, 2)

    const idImage = await axios.get('http://localhost:5174/api/image/getInfo/' + file.value.name)

    console.log(idImage)
  } catch (e) {
    error.value = e?.response?.data?.message || e.message
  } finally {
    loading.value = false
  }
}

</script>

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

  .text-green, #statusImage {
    color: green;
  }

  .text-red, #statusImageError {
    color: red;
  }

</style>
