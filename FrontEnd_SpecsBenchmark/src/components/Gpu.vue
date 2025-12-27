<template>
    <div id="main">
        <h1>GPU-Vergleich</h1>
        <div id="searchComponents">
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent1" v-model="gpu1" @select="getGpuByName(gpu1)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent2" v-model="gpu2" @select="getGpuByName(gpu2)"></SearchComponent>
            <span class="vs">vs.</span>
            <SearchComponent v-bind:nameList="gpuNames" id="searchComponent3" v-model="gpu3" @select="getGpuByName(gpu3)"></SearchComponent>
        </div>
        <div id="tables">
            <SpecsComponent v-bind:compareObject="objects[0]"></SpecsComponent>
            <SpecsComponent v-bind:compareObject="objects[1]"></SpecsComponent>
            <SpecsComponent v-bind:compareObject="objects[2]"></SpecsComponent>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import SpecsComponent from "./SpecsComponent.vue";
import SearchComponent from "./SearchComponent.vue";
import type { Gpu } from "../domain/CompareObjects";

const gpuNames = ref([]);
const gpu1 = ref("");
const gpu2 = ref("");
const gpu3 = ref("");
const objects = ref<(Gpu)[]>([]);

onMounted(() => {
    getGpuNameList();
});

computed(() => {
    console.log(gpu1.value)
})

async function getGpuNameList() {
    const response = await axios.get('http://localhost:5174/api/gpu/namelist');

    gpuNames.value = response.data;
}

async function getGpuByName(name: string) {
    const response = await axios.get("http://localhost:5174/api/gpu/getByName", {
    params: { name }
    });

    objects.value.push(response.data)

    console.log(objects.value)
}
</script>

<style lang="scss" scoped>
#main {
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