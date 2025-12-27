package de.computerspecs.backend.dto;

import java.time.LocalDate;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class GpuDTO {
    
    private String slug;           
    private String name;

    private String manufacturer;
    private String gpuName;
    private String architecture;
    private String generation;
    private String foundry;

    private Integer processNm;
    private Double transistorCountMillion;
    private Double transistorDensityMillionPerMm2;
    private Double dieSizeMm2;
    private String chipPackage;
    private LocalDate releaseDate;
    private String busInterface;

    private Double baseClockMhz;
    private Double boostClockMhz;
    private Double memoryClockMhz;

    private Double memorySizeGb;
    private Integer memorySizeMb;           
    private Integer memoryBusBits;
    private Double memoryBandwidthGbs;
    private String memoryType;

    private Integer shadingUnits;
    private Integer tmu;
    private Integer rop;
    private Integer sm;
    private Integer tensorCores;
    private Integer rtCores;

    private Double l1CacheKb;
    private Double l2CacheMb;

    private Integer tdpW;
    private Double boardLengthMm;
    private Double boardWidthMm;
    private String boardSlotWidth;
    private Integer suggestedPsuW;
    private String powerConnectors;
    private String displayConnectors;

    private Integer directxMajor, directxMinor;
    private Integer openglMajor, openglMinor;
    private Integer vulkanMajor, vulkanMinor;
    private Integer openclMajor, openclMinor;
    private Integer cudaMajor, cudaMinor;
    private Integer shaderModelMajor, shaderModelMinor;

    private Double pixelRateGpixelS;
    private Double textureRateGtexelS;
    private Double halfFloatGflops;
    private Double singleFloatGflops;
    private Double doubleFloatGflops;

    private String tpuId;
    private String tpuUrl;
}
