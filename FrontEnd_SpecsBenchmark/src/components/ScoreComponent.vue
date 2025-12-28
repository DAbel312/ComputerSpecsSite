<template>
    <div id="mainScore">
        <div>Gaming-Score</div>
        <div id="gamingScore">
            {{ Math.ceil(props.valueGaming) }}
        </div>
        <div id="gamingScoreRelation" :v-if="!(nullCountRef === 2 || nullCountRef === 3)"></div>
        <div>Compute-Score</div>
        <div id="computeScore">
            {{ Math.ceil(props.valueCompute) }}
        </div>
        <div id="computeScoreRelation" :v-if="!(nullCountRef === 2 || nullCountRef === 3)"></div>
        <div>Efficiency-Score</div>
        <div id="computeEfficiencyScore">
            {{ Math.ceil(props.computeEfficiencyScore) }}
        </div>
        <div id="efficiencyScoreRelation" :v-if="!(nullCountRef === 2 || nullCountRef === 3)"></div>
    </div>
</template>

<script setup lang="ts">
import type { Gpu, Cpu, Ssd } from "../domain/CompareObjects";
import { onMounted, ref } from "vue";

const props = withDefaults(defineProps<{
  valueGaming: number;
  valueCompute: number;
  computeEfficiencyScore: number;
  objectGpu: Gpu[] | null;
}>(),{
    valueGaming: 0,
    valueCompute: 0,
    computeEfficiencyScore: 0,
})

const nullCountRef = ref<number>();

const gamingScore = document.getElementById('gamingScoreRelation');
const computeScore = document.getElementById('computeScoreRelation');
const efficiencyScore = document.getElementById('efficiencyScoreRelation');

onMounted(() => {
    calculateScoreRelations(props.objectGpu);
});

function calculateScoreRelations(gpu: Gpu[] | null) {
    const nullCount = gpu?.filter(x => x === null).length;

    nullCountRef.value = nullCount;
}
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
}
</style>