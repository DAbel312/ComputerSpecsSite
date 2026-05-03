<template>
    <div id="main">
        <div id="gpus">
            <table id="allGpuTable">
                <thead>
                    <tr>
                    <th>
                        Hersteller 
                        <span id="sortButton" @click="getGpusSorted('manufacturer,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('manufacturer,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Name 
                        <span id="sortButton" @click="getGpusSorted('name,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('name,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Gaming-Score 
                        <span id="sortButton" @click="getGpusSorted('gamingScore,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('gamingScore,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Gaming-Efficiency-Score 
                        <span id="sortButton" @click="getGpusSorted('gamingEfficiency,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('gamingEfficiency,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Compute-Score 
                        <span id="sortButton" @click="getGpusSorted('computeScore,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('computeScore,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Compute-Efficiency-Score 
                        <span id="sortButton" @click="getGpusSorted('computeEfficiency,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('computeEfficiency,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Base Takt in mhz 
                        <span id="sortButton" @click="getGpusSorted('baseClockMhz,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('baseClockMhz,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Boost Takt in mhz 
                        <span id="sortButton" @click="getGpusSorted('boostClockMhz,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('boostClockMhz,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Memory Takt in mhz 
                        <span id="sortButton" @click="getGpusSorted('memoryClockMhz,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('memoryClockMhz,desc', currentPage)">&#x2B07</span>
                    </th>
                    <th>
                        Grafikspeicher in mb 
                        <span id="sortButton" @click="getGpusSorted('memorySizeMb,asc', currentPage)">&#x2B06</span>
                        <span id="sortButton" @click="getGpusSorted('memorySizeMb,desc', currentPage)">&#x2B07</span>
                    </th>
                    </tr>
                </thead>
                <tbody v-for="gpu in gpus">
                    <td>{{ gpu?.manufacturer }}</td>
                    <td>{{ gpu?.name }}</td>
                    <td>{{ gpu?.gamingScore }}</td>
                    <td>{{ gpu?.gamingEfficiency }}</td>
                    <td>{{ gpu?.computeScore }}</td>
                    <td>{{ gpu?.computeEfficiency }}</td>
                    <td>{{ gpu?.baseClockMhz }}</td>
                    <td>{{ gpu?.boostClockMhz }}</td>
                    <td>{{ gpu?.memoryClockMhz }}</td>
                    <td>{{ gpu?.memorySizeMb }}</td>
                </tbody>
            </table>
        </div>
        <div id="pages">
            <span 
                v-for="i in PageNumbers" @click="getGpusSorted('gamingScore,desc', i - 1)"
                :class="{ active: currentPage === i - 1}"
            >
                {{ i }}
            </span>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import axios from "axios";
import type { Gpu } from "../domain/CompareObjects";

const PageNumbers = ref<number>();
const gpus = ref<(Gpu | null)[]>([]);
const currentPage = ref<number>(0);

onMounted(() => {
    getGpusSorted("gamingScore,desc", 0)
    getPageNumbers();
});

/**
 * gets gpus from backend with page and sort function
 * @param sort 
 * @param page 
 */

async function getGpusSorted(sort: String, page: number) {

    const response = await axios.get('http://localhost:5174/api/gpu/getAllGpus?sort=' + sort + "&page=" + page + "&size=50");

    gpus.value = response.data.content;

    currentPage.value = page;
}

/**
 * gets number of pages for pagination from backend
 */

async function getPageNumbers() {
    const response = await axios.get('http://localhost:5174/api/gpu/namelist');

    PageNumbers.value = Math.floor((response.data.length) / 50);
}
</script>

<style lang="scss">
#main {
    padding: 70px 70px 70px 70px;

    #pages {
        margin-top: 70px;
        text-align: center;
    }

    #allGpuTable {
        border-collapse: collapse;
        border-radius: 13px;
        box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
        background-color: white;
        width: 100%;
        line-height: 150%;
        thead {
            tr {
                height: 60px;
                border-bottom: 1px solid var(--primaryDarkerBackgroundColor1);
            }
            th {
                width: 10%;
            }
        }

        tbody {
            width: 60px;
            padding: 20px 20px;
            border-bottom: 1px solid var(--primaryDarkerBackgroundColor1);

            &:last-of-type {
                border-bottom: none;
            }
            td {
                text-align: center;
            }
        }
    }

    #sortButton {
        cursor: pointer;
    }

    .active {
        font-weight: bold;
    }
}
</style>