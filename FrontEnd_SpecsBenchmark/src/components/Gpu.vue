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
            <ScoreComponent :valueGaming="objects[0]?.gamingScore ?? 0" 
                            :valueCompute="objects[0]?.computeScore ?? 0" 
                            :valueComputeEfficiencyScore="objects[0]?.computeEfficiency ?? 0"
                            :valueGamingEfficiencyScore="objects[0]?.gamingEfficiency ?? 0"
                            :objectGpu="objects.filter(obj => obj !== null)" 
                            :gamingScoreRelation="objects[0]?.gamingScoreRelation ?? 0" 
                            :computeScoreRelation="objects[0]?.computeScoreRelation ?? 0" 
                            :computeEfficiencyScoreRelation="objects[0]?.computeEfficiencyScoreRelation ?? 0" 
                            :gamingEfficiencyScoreRelation="objects[0]?.gamingEffiencyScoreRelation ?? 0"
                            :showScore="showScore"
                            class="scoreComponent1">
            </ScoreComponent>
            <ScoreComponent :valueGaming="objects[1]?.gamingScore ?? 0" 
                            :valueCompute="objects[1]?.computeScore ?? 0" 
                            :valueComputeEfficiencyScore="objects[1]?.computeEfficiency ?? 0"
                            :valueGamingEfficiencyScore="objects[1]?.gamingEfficiency ?? 0"
                            :objectGpu="objects.filter(obj => obj !== null)" 
                            :gamingScoreRelation="objects[1]?.gamingScoreRelation ?? 0" 
                            :computeScoreRelation="objects[1]?.computeScoreRelation ?? 0" 
                            :computeEfficiencyScoreRelation="objects[1]?.computeEfficiencyScoreRelation ?? 0" 
                            :gamingEfficiencyScoreRelation="objects[1]?.gamingEffiencyScoreRelation ?? 0"
                            :showScore="showScore"
                            class="scoreComponent2">
            </ScoreComponent>
            <ScoreComponent :valueGaming="objects[2]?.gamingScore ?? 0" 
                            :valueCompute="objects[2]?.computeScore ?? 0" 
                            :valueComputeEfficiencyScore="objects[2]?.computeEfficiency ?? 0"
                            :valueGamingEfficiencyScore="objects[2]?.gamingEfficiency ?? 0"
                            :objectGpu="objects.filter(obj => obj !== null)" 
                            :gamingScoreRelation="objects[2]?.gamingScoreRelation ?? 0" 
                            :computeScoreRelation="objects[2]?.computeScoreRelation ?? 0" 
                            :computeEfficiencyScoreRelation="objects[2]?.computeEfficiencyScoreRelation ?? 0" 
                            :gamingEfficiencyScoreRelation="objects[2]?.gamingEffiencyScoreRelation ?? 0"
                            :showScore="showScore"
                            class="scoreComponent3">
            </ScoreComponent>
        </div>
        <div id="tables">
            <SpecsComponent v-if="objects[0]" :compareObject="objects[0]" id="table1"/>
            <SpecsComponent v-if="objects[1]" :compareObject="objects[1]" id="table2"/>
            <SpecsComponent v-if="objects[2]" :compareObject="objects[2]" id="table3"/>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, watch, nextTick } from "vue";
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
const showScore = ref<boolean>();

onMounted(() => {
    getGpuNameList();
    calculateScoreRelations(objects.value);
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
}

watch(objects, () => {
  calculateScoreRelations(objects.value);
}, { deep: true });

// Zuerst die beiden GPU Objekte holen, dann die Scores vergleichen, dann beim höchsten Score 100 Setzen, dann ausrechnen, wie viel % langsamer die andere ist
function calculateScoreRelations(gpu: (Gpu | null)[]) {
    const nullCount = gpu.filter(x => x === null).length;

    if (nullCount === 3 || nullCount === 2) {
        showScore.value = false;
        return;
    }

    showScore.value = true;

    if (nullCount === 1 || nullCount === 0) {
        const nonNullIndices = objects.value
         .map((val, index) => (val !== null ? index : null))
         .filter((index): index is number => index !== null);

        if (nonNullIndices.length === 2) {
            const first = objects.value[nonNullIndices[0]]
            const second = objects.value[nonNullIndices[1]]

            if (first && second && first.gamingScore > second.gamingScore) {
                first.gamingScoreRelation = 100;
                second.gamingScoreRelation = (second.gamingScore / first.gamingScore) * 100
            } else if (first && second && first.gamingScore < second.gamingScore) {
                second.gamingScoreRelation = 100;
                first.gamingScoreRelation = (first.gamingScore / second.gamingScore) * 100
            }

            if (first && second && first.computeScore > second.computeScore) {
                first.computeScoreRelation = 100;
                second.computeScoreRelation = (second.computeScore / first.computeScore) * 100
            } else if (first && second && first.computeScore < second.computeScore) {
                second.computeScoreRelation = 100;
                first.computeScoreRelation = (first.computeScore / second.computeScore) * 100
            }

            if (first && second && first.computeEfficiency > second.computeEfficiency) {
                first.computeEfficiencyScoreRelation = 100;
                second.computeEfficiencyScoreRelation = (second.computeEfficiency / first.computeEfficiency) * 100
            } else if (first && second && first.computeEfficiency < second.computeEfficiency) {
                second.computeEfficiencyScoreRelation = 100;
                first.computeEfficiencyScoreRelation = (first.computeEfficiency / second.computeEfficiency) * 100
            }

             if (first && second && first.gamingEfficiency > second.gamingEfficiency) {
                first.gamingEffiencyScoreRelation = 100;
                second.gamingEffiencyScoreRelation = (second.gamingEfficiency / first.gamingEfficiency) * 100
            } else if (first && second && first.gamingEfficiency < second.gamingEfficiency) {
                second.gamingEffiencyScoreRelation = 100;
                first.gamingEffiencyScoreRelation = (first.gamingEfficiency / second.gamingEfficiency) * 100
            }
            return;
        }
        if (nonNullIndices.length === 3) {
            const gpus = nonNullIndices
                .map(i => objects.value[i])
                .filter((g): g is Gpu => g !== null);

            const applyRelation = <
                K extends keyof Gpu,
                R extends keyof Gpu
            >(valueKey: K, relationKey: R) => {
                const max = Math.max(...gpus.map(g => Number(g[valueKey])));

                gpus.forEach(g => {
                    (g[relationKey] as number) = (Number(g[valueKey]) / max) * 100;
                });
            };

            applyRelation("gamingScore", "gamingScoreRelation");
            applyRelation("computeScore", "computeScoreRelation");
            applyRelation("computeEfficiency", "computeEfficiencyScoreRelation");
            applyRelation("gamingEfficiency", "gamingEffiencyScoreRelation");

            return;
        }
    }
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