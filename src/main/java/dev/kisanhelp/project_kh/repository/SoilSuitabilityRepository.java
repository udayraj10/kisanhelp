package dev.kisanhelp.project_kh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.kisanhelp.project_kh.entity.SoilSuitability;
import dev.kisanhelp.project_kh.util.SoilType;

@Repository
public interface SoilSuitabilityRepository extends JpaRepository<SoilSuitability, Long> {

    @Query("SELECT s FROM SoilSuitability s WHERE s.crop.id = :cropId AND s.soilType = :soilType")
    Optional<SoilSuitability> findByCropIdAndSoilType(@Param("cropId") Long cropId,
            @Param("soilType") SoilType soilType);
}
