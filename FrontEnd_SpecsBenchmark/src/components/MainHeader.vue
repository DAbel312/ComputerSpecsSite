<template>
  <header id="responsive-header">
    <nav class="navbar">
      <div class="navdiv">
        <!-- <img src="../assets/Bilder/TechBenchLogo.webp" alt="TechBenchLogo" id="techBenchLogo"> -->
        <a class="name" id="titleHeader" href="#">
          Tech Bench
        </a>
        <ul>
          <li><a href="#" class="active">GPU</a></li>
          <li><a href="#">CPU</a></li>
          <li><a href="#">SSD</a></li>
          <li><a href="#">Kontakt</a></li>
          <li><a href="/#/impressum">Impressum</a></li>
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
  background: var(--primaryColor1);
  box-shadow: 0 4px 8px var(--primaryGreen1);
}

#responsive-header nav {
  background: var(--primaryColor1);
}

#responsive-header .navbar {
  padding: 15px 20px;
  overflow: hidden;
  padding-top: 30px;
  padding-bottom: 30px;
}

#responsive-header .navbar a:hover {
  color: var(--primaryGreen1);;
}

#responsive-header .navbar a:active {
  color: var(--primaryGreen1);;
}

#responsive-header .navdiv {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: 1fr 1fr;
}

#responsive-header .name {
  font-size: 35px;
  font-weight: 600;
  color: white;
  margin-bottom: 10px;
  margin-left: 50px;
  animation: slide-in 1s ease-out;
  cursor: default;
}

#titleHeader:hover, #titleHeader:active {
  color: white !important;
}

#responsive-header ul {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
  gap: 20px;
}

#responsive-header li {
  list-style: none;
  display: inline-block;
  margin-bottom: 10px;
  align-self: center;
}

#responsive-header li a {
  color: white;
  font-size: 20px;
  margin-right: 20px;
  transition: color 0.3s;
}

#responsive-header button {
  background-color: #37445d;
  margin-left: 10px;
  border-radius: 5px;
  padding: 10px 20px;
  width: 100px;
  height: 35px;
  border: none;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.1s, box-shadow 0.1s;
  align-self: center;
  margin-bottom: 10px;
}

#responsive-header button:hover {
  background-color: #abadb0;
  transform: scale(1.05);
  box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.2);
}

/* Responsive Design für Header */
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

@media (max-width: 970px) {
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
    background-color: white;
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
      background-color: var(--primaryGreen1);
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