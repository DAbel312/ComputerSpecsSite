<script setup>

</script>

<template>
    <div id="adminLogInDiv">
      <h4>Administrator Log In</h4>
      <input type="text" placeholder="Benutzername" id="userName" v-model="username">
      <input type="password" placeholder="Passwort" id="password" v-model="password">
      <input type="button" placeholder="Login" value="Login" id="loginButton" @click="login()">
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
    height: 250px;
    width: 30%;
    border: solid var(--primaryColor1);
    border-radius: 4px;
    justify-self: center;
    display: grid;
    grid-template-rows: 1fr 1fr 1fr 1fr;
    grid-template-columns: 1fr;
    margin-top: 100px;
    margin-bottom: 100px;

    input {
      width: 60%;
      height: 40px;
      justify-self: center;
      margin-top: 10px;
      outline: none;
    }

    h4 {
      text-align: center;
      margin-top: 10px;
    }

    #userName {
      margin-top: 20px;
    }

    #loginButton:hover {
      cursor: pointer;
    }
  }
</style>