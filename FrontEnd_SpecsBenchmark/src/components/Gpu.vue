<template>
    <div id="main">
        <h1>GPU-Vergleich</h1>
        <div id="searchComponents">
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent1" v-model="gpu1" @select="getGpuByName(gpu1, 0)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent2" v-model="gpu2" @select="getGpuByName(gpu2, 1)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent3" v-model="gpu3" @select="getGpuByName(gpu3, 2)"></SearchComponent>
        </div>
        <div id="scoreComponent">
            <ScoreComponent :valueGaming="objects[0]?.gamingScore ?? 0" :valueCompute="objects[0]?.computeScore ?? 0" :computeEfficiencyScore="objects[0]?.computeEffiencyScore ?? 0" :objectGpu="objects.filter(obj => obj !== null)" class="scoreComponent1"></ScoreComponent>
            <ScoreComponent :valueGaming="objects[1]?.gamingScore ?? 0" :valueCompute="objects[1]?.computeScore ?? 0" :computeEfficiencyScore="objects[1]?.computeEffiencyScore ?? 0" :objectGpu="objects.filter(obj => obj !== null)" class="scoreComponent2"></ScoreComponent>
            <ScoreComponent :valueGaming="objects[2]?.gamingScore ?? 0" :valueCompute="objects[2]?.computeScore ?? 0" :computeEfficiencyScore="objects[2]?.computeEffiencyScore ?? 0" :objectGpu="objects.filter(obj => obj !== null)" class="scoreComponent3"></ScoreComponent>
        </div>
        <div id="tables">
            <SpecsComponent v-if="objects[0]" :compareObject="objects[0]" id="table1"/>
            <SpecsComponent v-if="objects[1]" :compareObject="objects[1]" id="table2"/>
            <SpecsComponent v-if="objects[2]" :compareObject="objects[2]" id="table3"/>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import SpecsComponent from "./SpecsComponent.vue";
import SearchComponent from "./SearchComponent.vue";
import type { Gpu } from "../domain/CompareObjects";
import ScoreComponent from "./ScoreComponent.vue";

const gpuNames = ref([]);
const gpu1 = ref("");
const gpu2 = ref("");
const gpu3 = ref("");
const objects = ref<(Gpu | null)[]>([null, null, null]);

onMounted(() => {
    getGpuNameList();
});

/**
 * gets all gpu names
 */

async function getGpuNameList() {
    const response = await axios.get('http://localhost:5174/api/gpu/namelist');

    gpuNames.value = response.data;
}

/**
 * 
 * @param name gets gpu objects by gpu name from back-end
 * @param slot 
 */

async function getGpuByName(name: string, slot: number) {
    const response = await axios.get<Gpu>("http://localhost:5174/api/gpu/getByName", {
    params: { name }
    });

    const gpuWithType: Gpu = {
    ...response.data,
    type: "gpu",
    };

    objects.value[slot] = gpuWithType;
    objects.value = [...objects.value];

    calculateScores(gpuWithType, slot);
}

/**
 * 
 * @param gpu calculate gpu scores with gpu object
 * @param slot 
 */

function calculateScores(gpu: Gpu, slot: number) {

    const gflops = gpu.singleFloatGflops ?? 0;
    const pixelRate = gpu.pixelRateGpixelS ?? 0;
    const bandwidth = gpu.memoryBandwidthGbs ?? 0;
    const rtCores = gpu.rtCores ?? 0;
    const tensorCores = gpu.tensorCores ?? 0;
    const vramGb = gpu.memorySizeGb ?? 0;

    // architecture efficiency -> when newer architecture, better performance
    // IMPORTANT: check frequently and change values
    let archMultiplier = 1.0;
    const arch = (gpu.architecture || "").toLowerCase();
    
    if (arch.includes("ada lovelace") || arch.includes("blackwell") || arch.includes('rdna 4') || arch.includes('xe2')) archMultiplier = 1.5;
    else if (arch.includes("ampere") || arch.includes("rdna 3")) archMultiplier = 1.35;
    else if (arch.includes("turing") || arch.includes("rdna 2")) archMultiplier = 1.2;
    else if (arch.includes("pascal") || arch.includes("rdna 1")) archMultiplier = 1.0;
    else if (arch.includes("maxwell") || arch.includes("vega")) archMultiplier = 0.85;
    else archMultiplier = 0.7;

    // base performance calculation with raw performance, pixerate and bandwith
    const baseScore = (
        (gflops * 0.60) + 
        (pixelRate * 5.0) + // Faktor 5, da PixelRate numerisch kleiner ist als GFLOPS
        (bandwidth * 0.15)
    );

    // feature bonus with raytracing and tensor cores
    const featureBonus = (rtCores * 2.5) + (tensorCores * 0.5);

    // checks vram -> when too low, big bottleneck
    // IMPORTANT: check frequently and change values
    let vramMultiplier = 1.0;
    if (vramGb > 0 && vramGb < 4) vramMultiplier = 0.7;
    if (vramGb >= 12) vramMultiplier = 1.1;

    // final score
    const finalScore = (baseScore * archMultiplier + featureBonus) * vramMultiplier;

    const gpuWithScores: Gpu = {
        ...gpu,
        gamingScore: finalScore,
        computeScore: 0.6 * (gpu.singleFloatGflops ?? 0) +
                        0.4 * (gpu.memoryBandwidthGbs ?? 0),
        computeEffiencyScore: ((0.6 * (gpu.singleFloatGflops ?? 0) + 0.4 * (gpu.memoryBandwidthGbs ?? 0)) 
                                / (gpu.tdpW ?? 1)) * 100,
    };

    objects.value[slot] = gpuWithScores;
    objects.value = [...objects.value];
}
</script>

<style lang="scss" scoped>
#main {
min-height: 1400px;
    box-sizing: border-box;
    padding: 70px 70px 70px 70px;
    background-color: var(--primaryBackgroundColor1);
}

#searchComponents {
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    grid-template-rows: 1fr;
}

#searchComponent1 {
    padding: 0;
    padding-top: 40px;
}

#searchComponent2 {
    justify-self: center;
    padding-top: 40px;
}

#searchComponent3 {
    justify-self: end;
    padding-top: 40px;
}

.vs {
    justify-self: center;
    padding-top: 50px;
    font-weight: bold;
}

#scoreComponent {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr;
    margin-top: 40px;

    .scoreComponent1 {
        justify-self: flex-start;
    }

    .scoreComponent2 {
        justify-self: center;
    }

    .scoreComponent3 {
        justify-self: end;
    }
}

#tables {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr;
    margin-top: 40px;
}

#table1 {
    grid-column: 1;
}

#table2 {
    grid-column: 2;
}

#table3 {
    grid-column: 3;
}

#table1 > * {
    justify-self: flex-start;
    grid-column: 1;
}

#table2 > * {
    justify-self: center;
    grid-column: 2;
}

#table3 > * {
    justify-self: end;
    grid-column: 3;
}

@media (max-width: 1030px) {
    h1 {
        text-align: center;
    }

    #searchComponents {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-rows: repeat(5, 1fr);
    justify-items: center;
    }

    #searchComponent3 {
        justify-self: center;
    }
}
</style>