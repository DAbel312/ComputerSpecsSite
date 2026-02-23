package de.computerspecs.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.computerspecs.backend.entity.Gpu;
import de.computerspecs.backend.repository.GpuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GpuImportService {

  private final GpuRepository repo;
  private final ObjectMapper om = new ObjectMapper();

  public void importFromJson(Path path) throws Exception {
    if (!Files.exists(path)) {
      System.out.println("[GpuImport] Datei fehlt: " + path);
      return;
    }

    List<Map<String,Object>> rows = om.readValue(
        Files.readString(path),
        new TypeReference<List<Map<String,Object>>>() {});

    List<Gpu> entities = rows.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());

    repo.saveAll(entities);
    System.out.println("[GpuImport] Fertig: " + entities.size() + " Datensätze.");
  }

  private Gpu toEntity(Map<String, Object> r) {
    String name = s(r,"name");
    String tpuUrl = s(r,"tpu_url");
    String slug = (tpuUrl != null && !tpuUrl.isBlank())
        ? tpuUrl.replaceAll("/+$","").substring(tpuUrl.lastIndexOf('/')+1).split("\\.")[0]
        : slugify(name);

    Double memGb = d(r,"memory_size_gb");
    Integer memMb = (memGb == null) ? null : (int)Math.round(memGb * 1024.0);

    Double densityK = d(r,"transistor_density_k_mm2");
    Double densityM = (densityK == null) ? null : densityK * 1000.0;

    return Gpu.builder()
        .slug(slug)
        .name(name)
        .manufacturer(s(r,"manufacturer"))
        .gpuName(s(r,"gpu_name"))
        .architecture(s(r,"architecture"))
        .generation(s(r,"generation"))
        .foundry(s(r,"foundry"))
        .processNm(i(r,"process_size_nm"))
        .transistorCountMillion(d(r,"transistor_count_m"))
        .transistorDensityMillionPerMm2(densityM)
        .dieSizeMm2(d(r,"die_size_mm2"))
        .chipPackage(s(r,"chip_package"))
        .releaseDate(parseDate(s(r,"release_date")))
        .busInterface(s(r,"bus_interface"))

        .baseClockMhz(d(r,"base_clock_mhz"))
        .boostClockMhz(d(r,"boost_clock_mhz"))
        .memoryClockMhz(d(r,"memory_clock_mhz"))

        .memorySizeGb(memGb)
        .memorySizeMb(memMb)
        .memoryBusBits(i(r,"memory_bus_bits"))
        .memoryBandwidthGbs(d(r,"memory_bandwidth_gb_s"))
        .memoryType(s(r,"memory_type"))

        .shadingUnits(i(r,"shading_units"))
        .tmu(i(r,"texture_mapping_units"))
        .rop(i(r,"render_output_processors"))
        .sm(i(r,"streaming_multiprocessors"))
        .tensorCores(i(r,"tensor_cores"))
        .rtCores(i(r,"ray_tracing_cores"))

        .l1CacheKb(d(r,"l1_cache_kb"))
        .l2CacheMb(d(r,"l2_cache_mb"))

        .tdpW(i(r,"thermal_design_power_w"))
        .boardLengthMm(d(r,"board_length_mm"))
        .boardWidthMm(d(r,"board_width_mm"))
        .boardSlotWidth(s(r,"board_slot_width"))
        .suggestedPsuW(i(r,"suggested_psu_w"))
        .powerConnectors(s(r,"power_connectors"))
        .displayConnectors(s(r,"display_connectors"))

        .directxMajor(i(r,"directx_major_version"))
        .directxMinor(i(r,"directx_minor_version"))
        .openglMajor(i(r,"opengl_major_version"))
        .openglMinor(i(r,"opengl_minor_version"))
        .vulkanMajor(i(r,"vulkan_major_version"))
        .vulkanMinor(i(r,"vulkan_minor_version"))
        .openclMajor(i(r,"opencl_major_version"))
        .openclMinor(i(r,"opencl_minor_version"))
        .cudaMajor(i(r,"cuda_major_version"))
        .cudaMinor(i(r,"cuda_minor_version"))
        .shaderModelMajor(i(r,"shader_model_major_version"))
        .shaderModelMinor(i(r,"shader_model_minor_version"))

        .pixelRateGpixelS(d(r,"pixel_rate_gpixel_s"))
        .textureRateGtexelS(d(r,"texture_rate_gtexel_s"))
        .halfFloatGflops(coalesce(
            d(r,"half_float_performance_tflop_s"),
            d(r,"half_float_performance_gflop_s")))
        .singleFloatGflops(coalesce(
            d(r,"single_float_performance_tflop_s"),
            d(r,"single_float_performance_gflop_s")))
        .doubleFloatGflops(coalesce(
            d(r,"double_float_performance_gflop_s"),
            d(r,"double_float_performance_tflop_s")))

        .tpuId(s(r,"tpu_id"))
        .tpuUrl(tpuUrl)
        .build();
  }

  private static String s(Map<String,Object> r, String k){ Object v=r.get(k); return v==null?null:String.valueOf(v); }
  private static Integer i(Map<String,Object> r, String k){ Object v=r.get(k); if(v==null) return null; if(v instanceof Number n) return n.intValue(); try { return Integer.valueOf(v.toString()); } catch(Exception e){ return null; } }
  private static Double d(Map<String,Object> r, String k){ Object v=r.get(k); if(v==null) return null; if(v instanceof Number n) return n.doubleValue(); try { return Double.valueOf(v.toString()); } catch(Exception e){ return null; } }

  private static LocalDate parseDate(String iso){ return (iso==null||iso.isBlank())?null:LocalDate.parse(iso); }

  private static String slugify(String name){
    if (name==null) return null;
    return name.toLowerCase().replaceAll("[^a-z0-9]+","-").replaceAll("(^-|-$)","");
  }

  private static Double coalesce(Double tflops, Double gflops){
    if (tflops != null) return tflops * 1000.0;
    return gflops;
  }
}
