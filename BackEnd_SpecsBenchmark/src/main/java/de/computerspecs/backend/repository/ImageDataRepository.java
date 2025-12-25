package de.computerspecs.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import de.computerspecs.backend.entity.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name);

    @Query("SELECT i.id FROM ImageData i WHERE i.name = :name")
    List<Long> findImageIdsByName(@Param("name") String name);

}
