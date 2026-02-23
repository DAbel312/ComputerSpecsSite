<template>
  <footer>
    <div id="footerLinks">
      <h3>Links</h3>
      <p><a href="/#/impressum" class="footerLink">Impressum</a></p>
      <p><a href="#" class="footerLink">Kontakt</a></p>
      <p><a href="#" class="footerLink">Über diese Seite</a></p>
    </div>
    <div id="websiteLogo">
      <img src="../assets/Bilder/TechBenchLogo.webp" alt="TechBenchLogo" id="pclogo">
    </div>
  </footer>
</template>

<script setup>

</script>

<style scoped lang="scss">
footer {
  width: 100%;
  height: 170px;
  background-color: var(--primaryColor1);
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  display: grid;
  grid-template-columns: 6fr 1fr;
}

#footerLinks {
  place-items: center;
  margin-left: 50px;
  padding-top: 40px;
  grid-column: 1;
  justify-self: left;
  line-height: 150%;

  h3 {
    color: white;
  }
}

#websiteLogo {
  grid-column: 2;
  justify-self: end;
  padding-top: 45px;
}

.footerLink {
  color: var(--primaryBackgroundColor1)
}

.footerLink:hover, .footerLink:active {
  color: var(--primaryGreen1);
  transition: color 0.3s;
}

#pclogo {
  width: 120px;
  height: 120px;
}

@media (max-width: 768px) {
  #websiteLogo {
    display: none;
  }

  footer {
    grid-template-columns: 1fr;
  }

  #footerLinks {
    justify-self: center;
    margin-left: 0;
  }
}

@media (max-width: 430px) {
  #footerLinks {
    padding-top: 45px;
  }
}
</style>