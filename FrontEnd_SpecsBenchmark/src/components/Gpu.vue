<template>
    <div id="main">
        <h1>GPU-Vergleich</h1>
        <div id="searchComponents">
            <SearchComponent :nameList="gpuNames" id="searchComponent1" v-model="gpu1" @select="getGpuByName(gpu1, 0)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent :nameList="gpuNames" id="searchComponent2" v-model="gpu2" @select="getGpuByName(gpu2, 1)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent :nameList="gpuNames" id="searchComponent3" v-model="gpu3" @select="getGpuByName(gpu3, 2)"></SearchComponent>
        </div>
        <div id="emptyTables" v-if="!objects[0] && !objects[1] && !objects[2]">
            <h2>Keine GPUs ausgewählt</h2>
        </div>
        <div id="scoreComponent" :class="{ oneRow: objects.filter(obj => obj !== null).length === 1 , twoRows: objects.filter(obj => obj !== null).length === 2, threeRows: objects.filter(obj => obj !== null).length === 3 }">
            <ScoreComponent v-if="objects[0]"
                            :valueGaming="objects[0]?.gamingScore ?? 0" 
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
            <ScoreComponent v-if="objects[1]"
                            :valueGaming="objects[1]?.gamingScore ?? 0" 
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
            <ScoreComponent v-if="objects[2]"
                            :valueGaming="objects[2]?.gamingScore ?? 0" 
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
        <div id="importantSpecs" :class="{ oneRow: objects.filter(obj => obj !== null).length === 1 , twoRows: objects.filter(obj => obj !== null).length === 2, threeRows: objects.filter(obj => obj !== null).length === 3 }">
            <ImportantSpecsComponent
                            v-if="objects[0]"
                            class="firstImportant" 
                            firstAssignment="VRAM in GB" :firstValue="objects[0]?.memorySizeGb ?? 0"
                            secondAssignment="Base Clock in MHz" :secondValue="objects[0]?.baseClockMhz ?? 0"
                            thirdAssignment="Boost Clock in MHz" :thirdValue="objects[0]?.boostClockMhz ?? 0"
                            fourthAssignment="Memory Clock in MHz" :fourthValue="objects[0]?.memoryClockMhz ?? 0"
                            fifthAssignment="TDP in W" :fifthValue="objects[0]?.tdpW ?? 0"
                            sixthAssignment="ROPs" :sixthValue="objects[0]?.rop ?? 0"
                            seventhAssignment="TMUs" :seventhValue="objects[0]?.tmu ?? 0"
                            eigthAssignment="SMs" :eigthValue="objects[0]?.sm ?? 0">
            </ImportantSpecsComponent>
            <ImportantSpecsComponent 
                            v-if="objects[1]"
                            class="secondImportant" 
                            firstAssignment="VRAM in GB" :firstValue="objects[1]?.memorySizeGb ?? 0"
                            secondAssignment="Base Clock in MHz" :secondValue="objects[1]?.baseClockMhz ?? 0"
                            thirdAssignment="Boost Clock in MHz" :thirdValue="objects[1]?.boostClockMhz ?? 0"
                            fourthAssignment="Memory Clock in MHz" :fourthValue="objects[1]?.memoryClockMhz ?? 0"
                            fifthAssignment="TDP in W" :fifthValue="objects[1]?.tdpW ?? 0"
                            sixthAssignment="ROPs" :sixthValue="objects[1]?.rop ?? 0"
                            seventhAssignment="TMUs" :seventhValue="objects[1]?.tmu ?? 0"
                            eigthAssignment="SMs" :eigthValue="objects[1]?.sm ?? 0">
            </ImportantSpecsComponent>
            <ImportantSpecsComponent 
                            v-if="objects[2]"
                            class="thirdImportant" 
                            firstAssignment="VRAM in GB" :firstValue="objects[2]?.memorySizeGb ?? 0"
                            secondAssignment="Base Clock in MHz" :secondValue="objects[2]?.baseClockMhz ?? 0"
                            thirdAssignment="Boost Clock in MHz" :thirdValue="objects[2]?.boostClockMhz ?? 0"
                            fourthAssignment="Memory Clock in MHz" :fourthValue="objects[2]?.memoryClockMhz ?? 0"
                            fifthAssignment="TDP in W" :fifthValue="objects[2]?.tdpW ?? 0"
                            sixthAssignment="ROPs" :sixthValue="objects[2]?.rop ?? 0"
                            seventhAssignment="TMUs" :seventhValue="objects[2]?.tmu ?? 0"
                            eigthAssignment="SMs" :eigthValue="objects[2]?.sm ?? 0">
            </ImportantSpecsComponent>
        </div>
        <div id="importantInfo" :class="{ oneRow: objects.filter(obj => obj !== null).length === 1 , twoRows: objects.filter(obj => obj !== null).length === 2, threeRows: objects.filter(obj => obj !== null).length === 3 }">
            <ImportantInfoComponent 
                            v-if="objects[0]"
                            class="firstImportant"
                            firstAssignment="Hersteller" :firstValue="objects[0]?.manufacturer ?? 'null'"
                            secondAssignment="GPU-Name" :secondValue="objects[0]?.gpuName ?? 'null'"
                            thirdAssignment="Architecture" :thirdValue="objects[0]?.architecture ?? 'null'"
                            fourthAssignment="Generation" :fourthValue="objects[0]?.generation ?? 'null'"
                            fifthAssignment="Fertigung in nm" :fifthValue="objects[0]?.processNm ?? 'null'">
            </ImportantInfoComponent>
            <ImportantInfoComponent 
                            v-if="objects[1]"
                            class="secondImportant"
                            firstAssignment="Hersteller" :firstValue="objects[1]?.manufacturer ?? 'null'"
                            secondAssignment="GPU-Name" :secondValue="objects[1]?.gpuName ?? 'null'"
                            thirdAssignment="Architecture" :thirdValue="objects[1]?.architecture ?? 'null'"
                            fourthAssignment="Generation" :fourthValue="objects[1]?.generation ?? 'null'"
                            fifthAssignment="Fertigung in nm" :fifthValue="objects[1]?.processNm ?? 'null'">>
            </ImportantInfoComponent>
            <ImportantInfoComponent 
                            v-if="objects[2]"
                            class="thirdImportant"
                            firstAssignment="Hersteller" :firstValue="objects[2]?.manufacturer ?? 'null'"
                            secondAssignment="GPU-Name" :secondValue="objects[2]?.gpuName ?? 'null'"
                            thirdAssignment="Architecture" :thirdValue="objects[2]?.architecture ?? 'null'"
                            fourthAssignment="Generation" :fourthValue="objects[2]?.generation ?? 'null'"
                            fifthAssignment="Fertigung in nm" :fifthValue="objects[2]?.processNm ?? 'null'">>
            </ImportantInfoComponent>
        </div>
        <div id="tables" :class="{ oneRow: objects.filter(obj => obj !== null).length === 1 , twoRows: objects.filter(obj => obj !== null).length === 2, threeRows: objects.filter(obj => obj !== null).length === 3 }">
            <SpecsComponent v-if="objects[0]" :compareObject="objects[0]" id="table1"/>
            <SpecsComponent v-if="objects[1]" :compareObject="objects[1]" id="table2"/>
            <SpecsComponent v-if="objects[2]" :compareObject="objects[2]" id="table3"/>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import SpecsComponent from "./SpecsComponent.vue";
import SearchComponent from "./SearchComponent.vue";
import type { Gpu } from "../domain/CompareObjects";
import ScoreComponent from "./ScoreComponent.vue";
import ImportantSpecsComponent from "./ImportantSpecsComponent.vue";
import ImportantInfoComponent from "./ImportantInfoComponent.vue";

const props = withDefaults(defineProps<{
  gpuName?: string
}>(),{
  gpuName: ""
})

const gpuNames = ref([]);
const gpu1 = ref("");
const gpu2 = ref("");
const gpu3 = ref("");
const objects = ref<(Gpu | null)[]>([null, null, null]);
const showScore = ref<boolean>();

onMounted(() => {
    getGpuNameList();
    calculateScoreRelations(objects.value);
    getGpuByName(props.gpuName, 0);
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

    if (!name || name === "gpuName") {
        return;
    }

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

/**
 * calculates score relations between two or three gpus
 * @param gpusInput
 */

function calculateScoreRelations(gpusInput: (Gpu | null)[]) {
  const active = gpusInput.filter((g): g is Gpu => g !== null);
  showScore.value = active.length >= 2;
  if (!showScore.value) return;

  const applyRelation = <K extends keyof Gpu, R extends keyof Gpu>(
    valueKey: K,
    relationKey: R
  ) => {
    const values = active.map(g => Number(g[valueKey]) || 0);
    const max = Math.max(...values);

    const factor = max > 0 ? 100 / max : 0;

    active.forEach(g => {
      (g[relationKey] as number) = (Number(g[valueKey]) || 0) * factor;
    });
  };

  applyRelation("gamingScore", "gamingScoreRelation");
  applyRelation("computeScore", "computeScoreRelation");
  applyRelation("computeEfficiency", "computeEfficiencyScoreRelation");
  applyRelation("gamingEfficiency", "gamingEffiencyScoreRelation");
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

#importantSpecs, #importantInfo {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-template-rows: 1fr;
    margin-top: 40px;

    .secondImportant {
        justify-self: center;
    }

    .thirdImportant {
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

#emptyTables {
    margin-top: 100px;
    text-align: center;
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
        justify-self: center;
    }

    #searchComponent3 {
        justify-self: center;
    }

    #scoreComponent {
        display: grid;
        grid-template-columns: 1fr;
        justify-items: center;

        .scoreComponent1 {
            justify-self: center;
            margin-top: 40px;
        }

        .scoreComponent2 {
            margin-top: 40px;
        }

        .scoreComponent3 {
            justify-self: center;
            margin-top: 40px;
        }
    }

    .oneRow {
        grid-template-rows: 1fr;
    }

    .twoRows {
        grid-template-rows: repeat(2, 1fr);
    }

    .threeRows {
        grid-template-rows: repeat(3, 1fr);
    }

    #importantSpecs {
        display: grid;
        grid-template-columns: 1fr;
        justify-items: center;
    }

    #importantInfo {
        display: grid;
        grid-template-columns: 1fr;
        justify-items: center;
    }

    #tables {
        display: grid;
        grid-template-columns: 1fr;

        #table1, #table2, #table3 {
            grid-column: 1;
            margin-top: 40px;
        }

        #table1 > * {
            justify-self: center;
        }

        #table2 > * {
            justify-self: center;
        }

        #table3 > * {
            justify-self: center;
        }
    }

    .firstImportant, .secondImportant, .thirdImportant {
        justify-self: center !important;
        margin-top: 40px;
    }
}
</style>