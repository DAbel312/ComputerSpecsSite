<template>
  <footer>
    <!-- <img src="../assets/Bilder/TechBenchLogo.webp" alt="TechBenchLogo" id="pclogo"> -->
    <div id="footerLinks">
      <a href="/#/impressum" class="footerLink">Impressum</a>
      <a href="#" class="footerLink">Kontakt</a>
      <a href="#" class="footerLink">Über diese Seite</a>
    </div>
  </footer>
</template>

<script setup>

</script>

<style lang="scss">
footer {
  width: 100%;
  height: 170px;
  background-color: var(--primaryColor1);
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
}

#footerLinks {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr;
  place-items: center;
  margin-left: 20%;
  margin-right: 20%;
  padding-top: 140px;
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

@media (max-width: 600px) {
  #footerLinks {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(3, 1fr);
    padding-top: 50px;
  }

  .footerLink {
    margin-bottom: 8px;
  }
}

@media (max-width: 430px) {
  #footerLinks {
    padding-top: 45px;
  }
}
</style>