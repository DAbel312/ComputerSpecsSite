<template>
  <header id="responsive-header">
    <nav class="navbar">
      <div class="navdiv">
        <!-- <img src="../assets/Bilder/TechBenchLogo.webp" alt="TechBenchLogo" id="techBenchLogo"> -->
        <a class="name" id="titleHeader" href="#">
          Tech Bench
        </a>
        <ul>
          <li>
            <button class="button" @click="router.push('gpu')">
              GPU
            </button>
          </li>
          <li>
            <button class="button">
              CPU
            </button>
          </li>
          <li>
            <button class="button">
              SSD
            </button>
          </li>
          <li>
            <button class="button">
              Kontakt
            </button>
          </li>
          <li>
            <button class="button">
              <router-link to="/impressum" class="routerLink">
                Impressum
              </router-link>
            </button>
          </li>
        </ul>
        <div id="burgerMenu">
          <label for="toggleNav">
            <div class="white"></div>
            <div class="transparent"></div>
            <div class="white"></div>
            <div class="transparent"></div>
            <div class="white"></div>
          </label>
        </div>
      </div>
    </nav>
    <input type="checkbox" id="toggleNav">
    <div class="navResponsive" id="navResp">
      <ul>
        <li><a href="#" class="active">GPU</a></li>
        <li><a href="#">CPU</a></li>
        <li><a href="#">SSD</a></li>
        <li><a href="#">Kontakt</a></li>
        <li><a href="/#/impressum">Impressum</a></li>
      </ul>
    </div>
  </header>
</template>

<script setup>
import router from "../router/index.js";
</script>

<style lang="scss">
@use "../assets/colors.scss";

#responsive-header * {
  margin: 0;
  padding: 0;
  text-decoration: none;
  box-sizing: border-box;
}

#responsive-header {
  position: relative;
  z-index: 20;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}

#responsive-header nav {
  background: white;
}

#responsive-header .navbar {
  padding: 15px 20px;
  overflow: hidden;
  padding-top: 30px;
  padding-bottom: 30px;
}

.button {
  padding: 15px 20px !important;
  border: none;
  outline: none;
  background-color: #151515;
  color: #eee;
  border-radius: 7px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.25s ease-out;
  width: 110px;
}

.button:hover {
  transform: translateY(-3px);
}

.routerLink {
  color: white;
}

#responsive-header .navdiv {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: 1fr 1fr;
}

#responsive-header .name {
  font-size: 35px;
  font-weight: 600;
  color: black;
  margin-bottom: 10px;
  margin-left: 50px;
  animation: slide-in 1s ease-out;
  cursor: default;
}

#responsive-header ul {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  justify-items: end;
  margin-right: 50px;
  gap: 20px;
}

#responsive-header li {
  list-style: none;
  display: inline-block;
  margin-bottom: 10px;
  align-self: center;
}

#responsive-header #burgerMenu {
  display: none;
}

#responsive-header #navResp {
  display: none;
  width: 100%;
  height: 300px;
}

#responsive-header #toggleNav {
  display: none;
}

@media (max-width: 1000px) {
  #responsive-header .navdiv {
    display: grid;
    grid-template-columns: 1fr auto;
  }

  #responsive-header .name {
    grid-column: 1;
    grid-row: 1;
    text-align: center;
    padding-top: 17px;
  }

  #responsive-header #burgerMenu .white {
    width: 40px;
    height: 5px;
    background-color: black;
    display: block;
    cursor: pointer;
  }

  #responsive-header #burgerMenu .transparent {
    display: block;
    width: 40px;
    background-color: transparent;
    height: 5px;
    cursor: pointer;
  }

  #responsive-header #burgerMenu {
    grid-row: 1;
    align-self: center;
    grid-column: 2;
    display: block;
    justify-self: center;
  }

  #responsive-header #burgerMenu:hover, #responsive-header #burgerMenu:active {
    .white {
      background-color: black;
      transition: background-color 0.3s;
    }
  }

  #responsive-header ul {
    display: none;
  }

  #responsive-header li {
    margin-top: 7px;
  }

  @keyframes nav2toggle {
    0% {
      height: 0px;
    }
    100% {
      height: 300px;
    }
  }

  @keyframes toggleLink {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }

  #responsive-header #toggleNav:checked ~ #navResp {
    display: grid;
    position: absolute;
    animation-name: nav2toggle;
    animation-duration: 0.4s;
    background-color: var(--primaryColor2);
    z-index: 22;
  }

  #responsive-header #navResp ul {
    display: none;
  }

  #responsive-header #toggleNav:checked ~ #navResp ul {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
    place-items: center;
    display: grid;
    animation-name: toggleLink;
    animation-duration: 1.5s;
    gap: 0;
  }

  #responsive-header #navResp ul li a {
    margin-right: 0;
  }

  #responsive-header .navResponsive a:hover {
    color: var(--primaryGreen1);
  }

  #responsive-header .navResponsive a:active {
    color: var(--primaryGreen1);;
  }

  #responsive-header button {
    margin: 0;
  }
}

</style>