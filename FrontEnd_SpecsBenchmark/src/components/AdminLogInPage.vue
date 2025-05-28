<script setup>

import CsButton from "./CsButton.vue";
import CsInput from "./CsInput.vue";
</script>

<template>
    <div id="adminLogInDiv">
      <h4>Administrator Log In</h4>
      <CsInput placeholder="Benutzername" id="userName" v-model="username" width="60%" type="text"></CsInput>
      <CsInput placeholder="Password" type="password" id="password" v-model="password" width="60%"></CsInput>
      <cs-button @click="login()" content="Login" id="loginButton" width="45%"></cs-button>
    </div>
</template>

<script>

import axios from "axios";
import router from "../router/index.js";

/**
 * sends request to back-end for admin log in
 * sets jwt if request is success
 */

export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:5174/login', {
          username: this.username,
          password: this.password
        });

        localStorage.setItem('jwt', response.data.token);

        router.push("/admin");
      } catch (error) {
        alert("Login fehlgeschlagen")
        console.error(error);
      }
    }
  }
}

</script>

<style scoped lang="scss">

  #adminLogInDiv {
    height: 330px;
    width: 45%;
    box-shadow: var(--primaryGreen1) 0px 10px 20px, var(--primaryGreen1) 0px 6px;
    justify-self: center;
    display: grid;
    grid-template-rows: 1fr 1fr 1fr 1fr;
    grid-template-columns: 1fr;
    margin-top: 220px;
    margin-bottom: 220px;
    background-color: var(--primaryBackgroundColor1);
    border-radius: 12px;

    h4 {
      text-align: center;
      margin-top: 25px;
    }

    #userName {
      margin-top: 20px;
      justify-self: center;
    }

    #loginButton {
      justify-self: center;
      height: 40px;
      margin-top: 10px;
    }

    #password {
      justify-self: center;
    }
  }
</style>