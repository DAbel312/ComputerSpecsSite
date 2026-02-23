<template>
    <div id="mainScore">
        <div>Gaming-Score</div>
        <div id="gamingScore">
            {{ Math.ceil(props.valueGaming) }}
        </div>
        <div id="gamingScoreRelation" v-if="showScore">
            <div id="blueBarGamingScore" :style="{ width: gamingScoreRelation + '%' }">{{ Math.floor(gamingScoreRelation) + '%' }}</div>
        </div>
        <div>Compute-Score</div>
        <div id="computeScore">
            {{ Math.ceil(props.valueCompute) }}
        </div>
        <div id="computeScoreRelation" v-if="showScore">
            <div id="blueBarComputeScore" :style="{ width: computeScoreRelation + '%' }">{{ Math.floor(computeScoreRelation) + '%'}}</div>
        </div>
        <div>Compute-Efficiency-Score</div>
        <div id="computeEfficiencyScore">
            {{ Math.ceil(props.valueComputeEfficiencyScore) }}
        </div>
        <div id="computeEfficiencyScoreRelation" v-if="showScore">
            <div id="blueBarEfficiencyCompute" :style="{ width: computeEfficiencyScoreRelation + '%' }">{{ Math.floor(computeEfficiencyScoreRelation) + '%' }}</div>
        </div>
        <div>Gaming-Efficiency-Score</div>
        <div id="gamingEfficiencyScore">
            {{ Math.ceil(props.valueGamingEfficiencyScore) }}
        </div>
        <div id="gamingEfficiencyScoreRelation" v-if="showScore">
            <div id="blueBarEfficiencyGaming" :style="{ width: gamingEfficiencyScoreRelation + '%' }">{{ Math.floor(gamingEfficiencyScoreRelation) + '%'}}</div>
        </div>
    </div>
</template>

<script setup lang="ts">
import type { Gpu, Cpu, Ssd } from "../domain/CompareObjects";
import { onMounted, ref, watch  } from "vue";

const props = withDefaults(defineProps<{
  valueGaming: number;
  valueCompute: number;
  valueComputeEfficiencyScore: number;
  valueGamingEfficiencyScore: number;
  objectGpu: Gpu[] | null;
  gamingScoreRelation: number;
  computeScoreRelation: number;
  computeEfficiencyScoreRelation: number;
  gamingEfficiencyScoreRelation: number;
  showScore: boolean | undefined;
}>(),{
    valueGaming: 0,
    valueCompute: 0,
    computeEfficiencyScore: 0,
    gamingScore: 0,
    computeScore: 0,
    efficiencyScore: 0,
    showScore: false
})

watch(() => props.objectGpu, () => {
    console.log(props.objectGpu)
}, { deep: true });
</script>

<style scoped lang="scss">
* {
    text-align: center;
}
#mainScore {
    background-color: white;
    height: fit-content;
    width: 90%;
    border-radius: 13px;
    box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
    padding-top: 10px;
    padding-bottom: 20px;
}

#gamingScoreRelation, #computeScoreRelation, #computeEfficiencyScoreRelation, #gamingEfficiencyScoreRelation {
    width: 80%;
    border: 1px solid var(--primaryDarkerBackgroundColor1);
    border-radius: 13px;
    margin-left: auto;
    margin-right: auto;

    #blueBarGamingScore, #blueBarComputeScore, #blueBarEfficiencyCompute, #blueBarEfficiencyGaming {
        border-radius: 13px;
        width: 0%;
        height: 20px;
        z-index: -10;
        background-color: rgb(106, 106, 255);
        color: white;
        font-weight: bold;
    }
}
</style>