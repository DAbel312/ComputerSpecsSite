export interface Gpu  {
    type: 'gpu'

    name: string
    manufacturer: string
    gpuName: string
    architecture: string
    generation: string
    foundry: string

    processNm: number | null
    transistorCountMillion: number | null
    transistorDensityMillionPerMm2: number | null
    dieSizeMm2: number | null
    chipPackage: string
    releaseDate: string
    busInterface: string

    baseClockMhz: number | null
    boostClockMhz: number | null
    memoryClockMhz: number | null

    memorySizeGb: number | null
    memorySizeMb: number | null
    memoryBusBits: number | null
    memoryBandwidthGbs: number | null
    memoryType: string

    shadingUnits: number | null
    tmu: number | null
    rop: number | null
    sm: number | null
    tensorCores: number | null
    rtCores: number | null

    l1CacheKb: number | null
    l2CacheMb: number | null

    tdpW: number | null
    boardLengthMm: number | null
    boardWidthMm: number | null
    boardSlotWidth: string
    suggestedPsuW: number | null
    powerConnectors: string
    displayConnectors: string

    directxMajor: number | null
    directxMinor: number | null
    openglMajor: number | null
    openglMinor: number | null
    vulkanMajor: number | null
    vulkanMinor: number | null
    openclMajor: number | null
    openclMinor: number | null
    cudaMajor: number | null
    cudaMinor: number | null
    shaderModelMajor: number | null
    shaderModelMinor: number | null

    pixelRateGpixelS: number | null
    textureRateGtexelS: number | null
    halfFloatGflops: number | null
    singleFloatGflops: number | null
    doubleFloatGflops: number | null

    tpuId: string
    tpuUrl: string

    gamingScore: number;
    computeScore: number;
    gamingEfficiency: number;
    computeEfficiency: number;

    gamingScoreRelation: number;
    computeScoreRelation: number;
    gamingEffiencyScoreRelation: number;
    computeEfficiencyScoreRelation: number;
}

export interface Cpu  {
    type: 'cpu'

    name: string
}

export interface Ssd  {
    type: 'ssd'
    
    name: string
}