<template>
    <div id="main">
        <table class="gpu-specs" v-if="props.compareObject?.type === 'gpu'">
            <thead>
                <tr>
                <th>Eigenschaft</th>
                <th>Wert</th>
                </tr>
            </thead>

            <tbody>
                <tr><td>Name</td><td>{{ props.compareObject?.name ?? "-" }}</td></tr>

                <tr><td>Hersteller</td><td>{{ props.compareObject?.manufacturer ?? "-" }}</td></tr>
                <tr><td>GPU-Name</td><td>{{ props.compareObject?.gpuName ?? "-" }}</td></tr>
                <tr><td>Architektur</td><td>{{ props.compareObject?.architecture ?? "-" }}</td></tr>
                <tr><td>Generation</td><td>{{ props.compareObject?.generation ?? "-" }}</td></tr>
                <tr><td>Foundry</td><td>{{ props.compareObject?.foundry ?? "-" }}</td></tr>

                <tr><td>Fertigung (nm)</td><td>{{ props.compareObject?.processNm ?? "-" }}</td></tr>
                <tr><td>Transistoren (Million)</td><td>{{ props.compareObject?.transistorCountMillion ?? "-" }}</td></tr>
                <tr><td>Transistordichte (Mio/mm²)</td><td>{{ props.compareObject?.transistorDensityMillionPerMm2 ?? "-" }}</td></tr>
                <tr><td>Die Size (mm²)</td><td>{{ props.compareObject?.dieSizeMm2 ?? "-" }}</td></tr>
                <tr><td>Chip Package</td><td>{{ props.compareObject?.chipPackage ?? "-" }}</td></tr>
                <tr><td>Release Date</td><td>{{ props.compareObject?.releaseDate ?? "-" }}</td></tr>
                <tr><td>Bus Interface</td><td>{{ props.compareObject?.busInterface ?? "-" }}</td></tr>

                <tr><td>Base Clock (MHz)</td><td>{{ props.compareObject?.baseClockMhz ?? "-" }}</td></tr>
                <tr><td>Boost Clock (MHz)</td><td>{{ props.compareObject?.boostClockMhz ?? "-" }}</td></tr>
                <tr><td>Memory Clock (MHz)</td><td>{{ props.compareObject?.memoryClockMhz ?? "-" }}</td></tr>

                <tr><td>Memory Size (GB)</td><td>{{ props.compareObject?.memorySizeGb ?? "-" }}</td></tr>
                <tr><td>Memory Size (MB)</td><td>{{ props.compareObject?.memorySizeMb ?? "-" }}</td></tr>
                <tr><td>Memory Bus (Bit)</td><td>{{ props.compareObject?.memoryBusBits ?? "-" }}</td></tr>
                <tr><td>Memory Bandwidth (GB/s)</td><td>{{ props.compareObject?.memoryBandwidthGbs ?? "-" }}</td></tr>
                <tr><td>Memory Type</td><td>{{ props.compareObject?.memoryType ?? "-" }}</td></tr>

                <tr><td>Shading Units</td><td>{{ props.compareObject?.shadingUnits ?? "-" }}</td></tr>
                <tr><td>TMU</td><td>{{ props.compareObject?.tmu ?? "-" }}</td></tr>
                <tr><td>ROP</td><td>{{ props.compareObject?.rop ?? "-" }}</td></tr>
                <tr><td>SM</td><td>{{ props.compareObject?.sm ?? "-" }}</td></tr>
                <tr><td>Tensor Cores</td><td>{{ props.compareObject?.tensorCores ?? "-" }}</td></tr>
                <tr><td>RT Cores</td><td>{{ props.compareObject?.rtCores ?? "-" }}</td></tr>

                <tr><td>L1 Cache (KB)</td><td>{{ props.compareObject?.l1CacheKb ?? "-" }}</td></tr>
                <tr><td>L2 Cache (MB)</td><td>{{ props.compareObject?.l2CacheMb ?? "-" }}</td></tr>

                <tr><td>TDP (W)</td><td>{{ props.compareObject?.tdpW ?? "-" }}</td></tr>
                <tr><td>Board Length (mm)</td><td>{{ props.compareObject?.boardLengthMm ?? "-" }}</td></tr>
                <tr><td>Board Width (mm)</td><td>{{ props.compareObject?.boardWidthMm ?? "-" }}</td></tr>
                <tr><td>Slot Width</td><td>{{ props.compareObject?.boardSlotWidth ?? "-" }}</td></tr>
                <tr><td>Suggested PSU (W)</td><td>{{ props.compareObject?.suggestedPsuW ?? "-" }}</td></tr>
                <tr><td>Power Connectors</td><td>{{ props.compareObject?.powerConnectors ?? "-" }}</td></tr>
                <tr><td>Display Connectors</td><td>{{ props.compareObject?.displayConnectors ?? "-" }}</td></tr>

                <tr><td>DirectX</td><td>{{ (props.compareObject?.directxMajor ?? "-") }}.{{ (props.compareObject?.directxMinor ?? "-") }}</td></tr>
                <tr><td>OpenGL</td><td>{{ (props.compareObject?.openglMajor ?? "-") }}.{{ (props.compareObject?.openglMinor ?? "-") }}</td></tr>
                <tr><td>Vulkan</td><td>{{ (props.compareObject?.vulkanMajor ?? "-") }}.{{ (props.compareObject?.vulkanMinor ?? "-") }}</td></tr>
                <tr><td>OpenCL</td><td>{{ (props.compareObject?.openclMajor ?? "-") }}.{{ (props.compareObject?.openclMinor ?? "-") }}</td></tr>
                <tr><td>CUDA</td><td>{{ (props.compareObject?.cudaMajor ?? "-") }}.{{ (props.compareObject?.cudaMinor ?? "-") }}</td></tr>
                <tr><td>Shader Model</td><td>{{ (props.compareObject?.shaderModelMajor ?? "-") }}.{{ (props.compareObject?.shaderModelMinor ?? "-") }}</td></tr>

                <tr><td>Pixel Rate (GPixel/s)</td><td>{{ props.compareObject?.pixelRateGpixelS ?? "-" }}</td></tr>
                <tr><td>Texture Rate (GTexel/s)</td><td>{{ props.compareObject?.textureRateGtexelS ?? "-" }}</td></tr>
                <tr><td>Half Float (GFLOPS)</td><td>{{ props.compareObject?.halfFloatGflops ?? "-" }}</td></tr>
                <tr><td>Single Float (GFLOPS)</td><td>{{ props.compareObject?.singleFloatGflops ?? "-" }}</td></tr>
                <tr><td>Double Float (GFLOPS)</td><td>{{ props.compareObject?.doubleFloatGflops ?? "-" }}</td></tr>

                <tr><td>TPU ID</td><td>{{ props.compareObject?.tpuId ?? "-" }}</td></tr>
                <tr><td>TPU URL</td>
                <td>
                    <a v-if="props.compareObject?.tpuUrl" :href="props.compareObject.tpuUrl" target="_blank" rel="noopener">
                    {{ props.compareObject.tpuUrl }}
                    </a>
                    <span v-else>-</span>
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Gpu, Cpu, Ssd } from "../domain/CompareObjects";

const props = withDefaults(defineProps<{
  compareObject?: Gpu;
}>(),{})
</script>

<style scoped lang="scss">

</style>