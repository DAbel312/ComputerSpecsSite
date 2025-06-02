<script setup>

</script>

<template>
  <footer>
    <div id="footerLinks">
      <a href="#" class="footerLink">Impressum</a>
      <a href="#" class="footerLink">Kontakt</a>
      <a href="#" class="footerLink">Über diese Seite</a>
    </div>
    <!-- <img src="../assets/Bilder/LogoPage.webp" alt="PC Logo" id="pclogo"> -->
  </footer>
</template>

<style lang="scss">
footer {
  width: 100%;
  height: 170px;
  background-color: var(--primaryColor1);
  box-shadow: 0 0 10px var(--primaryGreen1);
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