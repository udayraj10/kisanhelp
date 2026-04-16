package dev.kisanhelp.project_kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.kisanhelp.project_kh.entity.Crop;
import java.util.Optional;
import java.util.List;
import dev.kisanhelp.project_kh.util.SoilType;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long>, JpaSpecificationExecutor<Crop> {

        Optional<Crop> findByCropName(String cropName);

        @Query("SELECT c.cropName FROM Crop c " +
                        "JOIN c.soilSuitabilities s " +
                        "WHERE s.soilType = :soilType " +
                        "AND c.waterNeedMM < 350")
        List<String> findRecommendedCrops(
                        @Param("soilType") SoilType soilType);

        @Query("SELECT c FROM Crop c JOIN c.soilSuitabilities s WHERE s.soilType = :soilType AND s.isSuitable = true")
        List<Crop> findBySoilSuitabilities(@Param("soilType") SoilType soilType);

        @Query("SELECT c FROM Crop c JOIN c.seasons s WHERE s.name = :season")
        List<Crop> findBySeason(@Param("season") String season);

        List<Crop> findByCategory(@Param("category") String category);

}
