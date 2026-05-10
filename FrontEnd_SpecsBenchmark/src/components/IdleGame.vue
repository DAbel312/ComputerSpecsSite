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
            <p id="pricePerGpu">{{ formatNumber(state.pricePerGpu) }} €/GPU</p>
        </div>

        <div id="upgradeDiv">
            <div id="availableUpgradeDiv" v-for="upgrade in availableOneTimeUpgrades" @click="buyOneTimeUpgrade(upgrade.id)" :class="{ disabled: upgrade.cost > state.money }">
                <p>{{ upgrade.name }}</p>
                <p>{{ upgrade.description }}</p>
                <p>{{ upgrade.cost }} €</p>
            </div>
        </div>

        <div id="resetDiv">
            <h2>Spielstand zurücksetzen</h2>
            <p>Du kannst Dein Spiel hier zurücksetzen, um Prestigepoints zu erhalten, damit Produktion und Verkauf steigen.</p>
            <p>Anzahl der Prestigepoints: {{ state.prestigePoints }}</p>
            <p id="prestigePoints1">Mögliche Prestigepoints:</p>
            <p id="prestigePoints2">{{ potentialPrestigePoints }}</p>
            <button @click="prestigeReset" id="resetButton" :disabled="potentialPrestigePoints <= 0">Prestige reset</button>
        </div>

        <div id="moreNews">
            <RandomNews></RandomNews>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useGpuIdleGame } from '../composables/useGpuIdleGame';
import RandomNews from "./RandomNews.vue";

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
  purchasedOneTimeUpgrades,
  buyOneTimeUpgrade,
  potentialPrestigePoints,
  prestigeReset
} = useGpuIdleGame();

function formatNumber(value: number): string {
  return new Intl.NumberFormat("de-DE", {
    maximumFractionDigits: 0,
  }).format(value);
}
</script>

<style lang="scss" scoped>
h1 {
    text-align: center;
    margin-top: 20px;
}

#main {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr 1fr;
    justify-items: center;
    padding: 70px 0px 0px 0px;
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

    #pricePerGpu {
        border-bottom-left-radius: 5px;
        border-bottom-right-radius: 5px;
    }
}

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

button:hover {
    transform: translateY(-3px);
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
}

#upgradeDiv {
    background-color: var(--primaryBackgroundColor1);
    height: 350px;
    border-radius: 5px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    width: 75%;
    box-sizing: border-box;
    padding: 10px 10px 10px 10px;
    overflow-y: auto;

    #availableUpgradeDiv {
        background-color: #151515;
        border-radius: 5px;
        box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
        width: 90%;
        min-height: 70px;
        height: fit-content;
        box-sizing: border-box;
        padding-top: 10px;
        justify-self: anchor-center;
        margin-top: 10px;
        color: #eee;

        p {
            text-align: center;
        }
    }

    #availableUpgradeDiv:hover {
            scale: 1.03;
            cursor: pointer;
        }

    .disabled {
        background-color: var(--primaryDarkerBackgroundColor1) !important;
    }

    .disabled:hover {
        cursor: default !important;
        scale: none !important;
    }
}

#resetDiv {
    background-color: var(--primaryBackgroundColor1);
    min-height: 380px;
    height: fit-content;
    border-radius: 5px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    grid-column: 1 / 4;
    margin-top: 40px;
    box-sizing: border-box;
    padding: 10px 10px 10px 10px;
    width: 92%;
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: repeat(6, 1fr);

    h2 {
        text-align: center;
        word-wrap: break-word;
        margin-top: 20px;
    }

    p {
        text-align: center;
        word-wrap: break-word;
        margin-top: 40px;
    }

    #prestigePoints1, #prestigePoints2 {
        font-weight: bold;
        font-size: 1.3rem;
    }

    #resetButton {
        margin-top: 0;
        justify-self: anchor-center;
        margin-top: 20px;
    }
}

#moreNews {
    grid-column: 1 / 4;
    width: 100%;
    background-color: #ffffff;
    margin-top: 80px;
}

@media (max-width: 1000px) {
    #buttonDiv, #statsDiv, #upgradeDiv {
        grid-column: 1 / 4;
    }

    #resetDiv {
        width: 75%;
    }

    #main {
        grid-template-columns: 1fr;
        row-gap: 40px;
    }
}

@media (max-width: 400px) {
    #statsDiv, #buttonDiv, #upgradeDiv, #resetDiv {
        width: 90%;
    }
}
</style>