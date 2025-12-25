package de.computerspecs.backend.runner;

import de.computerspecs.backend.service.GpuImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class GpuImportRunner implements CommandLineRunner {

  private final GpuImportService service;

  @Value("${gpu.import.enabled-on-startup:true}")
  boolean enabled;

  @Value("${gpu.import.json-path:data/gpus.json}")
  String jsonPath;

  @Override
  public void run(String... args) throws Exception {
    if (!enabled) return;
    service.importFromJson(Path.of(jsonPath));
  }
}
