<template>
  <div id="mainImprint">
    <h1>Impressum</h1>
    <h2 id="firstH2">Angaben gemäß § 5 TMG</h2>
    <h3>Name</h3>
    <p>Dennis Abel</p>
    <h3>Anschrift</h3>
    <p>Max-Pechstein Straße 16</p>
    <p>31228 Peine</p>
    <p>Deutschland</p>
    <h3>Kontakt</h3>
    <p>E-Mail: dennis.abel04@gmail.com</p>
    <h3>Verantwortlich für den Inhalt nach § 55 Abs. 2 RStV</h3>
    <p>Dennis Abel</p>
    <p>Max-Pechstein Straße 16</p>
    <p>31228 Peine</p>
    <h3>Haftung für Inhalte</h3>
    <pre>Als Diensteanbieter sind wir gemäß § 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach §§ 8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit hinweisen.</pre>
    <h3>Haftung für Links</h3>
    <pre>Unser Angebot enthält Links zu externen Websites Dritter, auf deren Inhalte wir keinen Einfluss haben. Deshalb können wir für diese fremden Inhalte auch keine Gewähr übernehmen.</pre>
    <h3>Urheberrecht</h3>
    <pre>Die durch die Seitenbetreiber erstellten Inhalte und Werke auf diesen Seiten unterliegen dem deutschen Urheberrecht.</pre>
  </div>
</template>

<script setup>

</script>

<style lang="scss">
#mainImprint {
  width: 100%;
  height: 1300px;
  padding: 25px 25px 25px 25px;
  box-sizing: border-box;
  line-height: 150%;

  #firstH2 {
    margin-top: 15px;
  }

  h1, h2, h3 {
    color: var(--textGreen);
  }

  pre {
    word-break: break-word;
    white-space: pre-wrap;
  }
}


</style>