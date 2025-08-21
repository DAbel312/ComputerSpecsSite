package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<Gpu, String> {
    
}
