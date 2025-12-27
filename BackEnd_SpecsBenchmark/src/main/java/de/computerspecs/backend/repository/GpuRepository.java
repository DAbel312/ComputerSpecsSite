package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.Gpu;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GpuRepository extends JpaRepository<Gpu, String> {
    
    @Query("SELECT g.name FROM Gpu g")
    List<String> getGpuNameList();
}
