<template>
    <h1>GPU Tycoon</h1>
    <div id="main">

        <div id="buttonDiv">
            <button @click="produceGpu(1)" id="b1">GPU Herstellen</button>
            <button :disabled="state.money < marketingCost" @click="buyMarketingUpgrade" id="b2">Marketing verbessern — {{ formatNumber(marketingCost) }} €</button>
            <button :disabled="state.money < factoryCost" @click="buyFactory" id="b3">Fabrik ausbauen — {{ formatNumber(factoryCost) }} €</button>
            <button @click="increasePrice" id="b4">Preis erhöhen <br> 50 €</button>
            <button @click="decreasePrice" id="b5">Preis verringern 50 €</button>
            <button @click="resetGame" id="b6">Spiel zurücksetzen</button>
        </div>

        <div id="statsDiv">
            <p>Geld</p>
            <p>{{ formatNumber(state.money) }} €</p>
            <p>Lagerbestand</p>
            <p>{{ formatNumber(state.inventory) }} GPUs</p>
            <p>Produktion</p>
            <p>{{ state.gpuPerSecond.toFixed(2) }} GPUs/s</p>
            <p>Nachfrage</p>
            <p>{{ state.demand.toFixed(2) }} GPUs/s</p>
            <p>Verkaufspreis</p>
            <p>{{ formatNumber(state.pricePerGpu) }} €/GPU</p>
        </div>

        <div id="upgradeDiv">
            <p>{{ availableOneTimeUpgrades }}</p>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useGpuIdleGame } from '../composables/useGpuIdleGame';

const {
  state,
  marketingCost,
  factoryCost,
  buyMarketingUpgrade,
  buyFactory,
  resetGame,
  increasePrice,
  decreasePrice,
  produceGpu,
  availableOneTimeUpgrades,
  purchasedOneTimeUpgrades
} = useGpuIdleGame();

function formatNumber(value: number): string {
  return new Intl.NumberFormat("de-DE", {
    maximumFractionDigits: 0,
  }).format(value);
}
</script>

<style lang="scss">
h1 {
    text-align: center;
    margin-top: 20px;
}

#main {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr;
    justify-items: center;
}

#statsDiv {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: repeat(10, 1fr);;
    background-color: var(--primaryBackgroundColor1);
    height: 350px;
    border-radius: 5px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    width: 75%;
    box-sizing: border-box;

    :nth-child(even) {
        background-color: var(--primaryDarkerBackgroundColor1);
        font-weight: normal;
        font-size: 1rem;
    }

    p {
        text-align: center;
        font-weight: bold;
        font-size: 1.3rem;
        box-sizing: border-box;
        padding-top: 6px;
    }
}

#buttonDiv {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr 1fr 1fr 1fr 1fr;
    background-color: var(--primaryBackgroundColor1);
    height: 350px;
    border-radius: 5px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    width: 75%;
    box-sizing: border-box;
    padding: 10px 10px 20px 10px;


    button {
        padding: 10px 10px 10px 10px !important;
        margin-top: 20px;
        padding: 15px 20px;
        border: none;
        outline: none;
        background-color: #151515;
        color: #eee;
        border-radius: 7px;
        cursor: pointer;
        transition: all 0.25s ease-out;
        width: 85%;
        justify-self: center;
    }

    button:disabled {
        transition: none;
        background-color: var(--primaryDarkerBackgroundColor1);
    }

    button:disabled:hover {
        cursor: default;
        transform: none;
    }

    #b1 {
        grid-column: 1 / 3;
        grid-row: 1;
    }

    #b2 {
        grid-column: 1 / 3;
        grid-row: 2;
    }

    #b3 {
        grid-column: 1 / 3;
        grid-row: 3;
    }

    #b4 {
        grid-column: 1;
        grid-row: 4;
        width: 70%;
    }

    #b5 {
        grid-column: 2;
        grid-row: 4;
        width: 70%;
    }

    #b6 {
        grid-column: 1 / 3;
        grid-row: 5;
    }

button:hover {
        transform: translateY(-3px);
    }
}

#upgradeDiv {
    background-color: var(--primaryBackgroundColor1);
    height: 350px;
    border-radius: 5px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    width: 75%;
    box-sizing: border-box;
    padding: 10px 10px 10px 10px;
}

@media (max-width: 1000px) {
    #main {
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 1fr 1fr;
        row-gap: 40px;
    }
}

@media (max-width: 400px) {
    #statsDiv, #buttonDiv, #upgradeDiv {
        width: 110%;
    }
}
</style>