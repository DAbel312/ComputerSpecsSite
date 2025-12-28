package de.computerspecs.backend.repository;

import de.computerspecs.backend.dto.GpuDTO;
import de.computerspecs.backend.entity.Gpu;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<Gpu, String> {
    
    @Query("SELECT g.name FROM Gpu g")
    List<String> getGpuNameList();

    @Query("""
    SELECT new de.computerspecs.backend.dto.GpuDTO(
        g.slug,
        g.name,
        g.manufacturer,
        g.gpuName,
        g.architecture,
        g.generation,
        g.foundry,
        g.processNm,
        g.transistorCountMillion,
        g.transistorDensityMillionPerMm2,
        g.dieSizeMm2,
        g.chipPackage,
        g.releaseDate,
        g.busInterface,
        g.baseClockMhz,
        g.boostClockMhz,
        g.memoryClockMhz,
        g.memorySizeGb,
        g.memorySizeMb,
        g.memoryBusBits,
        g.memoryBandwidthGbs,
        g.memoryType,
        g.shadingUnits,
        g.tmu,
        g.rop,
        g.sm,
        g.tensorCores,
        g.rtCores,
        g.l1CacheKb,
        g.l2CacheMb,
        g.tdpW,
        g.boardLengthMm,
        g.boardWidthMm,
        g.boardSlotWidth,
        g.suggestedPsuW,
        g.powerConnectors,
        g.displayConnectors,
        g.directxMajor, g.directxMinor,
        g.openglMajor, g.openglMinor,
        g.vulkanMajor, g.vulkanMinor,
        g.openclMajor, g.openclMinor,
        g.cudaMajor, g.cudaMinor,
        g.shaderModelMajor, g.shaderModelMinor,
        g.pixelRateGpixelS,
        g.textureRateGtexelS,
        g.halfFloatGflops,
        g.singleFloatGflops,
        g.doubleFloatGflops,
        g.tpuId,
        g.tpuUrl
    )
    FROM Gpu g
    WHERE g.name = :name
    """)
    GpuDTO findGpuByName(@Param("name") String name);
}
