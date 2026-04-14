package dev.kisanhelp.project_kh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.kisanhelp.project_kh.dto.response.CropData;
import dev.kisanhelp.project_kh.dto.response.CropResponse;
import dev.kisanhelp.project_kh.entity.Crop;
import dev.kisanhelp.project_kh.exception.CropNotFoundException;
import dev.kisanhelp.project_kh.repository.CropRepository;
import dev.kisanhelp.project_kh.specification.CropSpecification;
import dev.kisanhelp.project_kh.util.SoilType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropService {

        private final CropRepository cropRepository;

        public Crop getCrop(String cropName) {
                return cropRepository.findByCropName(cropName)
                                .orElseThrow(() -> {
                                        return new CropNotFoundException("No crop found named: " + cropName);
                                });
        }

        public List<String> getRecommendedCrops(String soilType) {

                SoilType type = SoilType.fromString(soilType);
                return cropRepository.findRecommendedCrops(type);
        }

        public ResponseEntity<CropResponse<List<CropData>>> getFilteredCrops(String soilType, String season,
                        String category) {

                Specification<Crop> spec = Specification.where(null);

                if (soilType != null && !soilType.isEmpty()) {
                        spec = spec.and(CropSpecification.hasSoilType(SoilType.fromString(soilType)));
                }

                if (season != null && !season.isEmpty()) {
                        spec = spec.and(CropSpecification.hasSeason(season));
                }

                if (category != null && !category.isEmpty()) {
                        spec = spec.and(CropSpecification.hasCategory(category));
                }

                List<CropData> response = cropRepository.findAll(spec).stream()
                                .map(crop -> new CropData(
                                                crop.getId(),
                                                crop.getCropName(),
                                                crop.getCategory(),
                                                crop.getWeeksToHarvest(),
                                                crop.getMinTempCelsius().intValue(),
                                                crop.getMaxTempCelsius().intValue(),
                                                crop.getWaterNeedMM(),
                                                crop.getSeasons().stream()
                                                                .map(seasonEntity -> seasonEntity.getName())
                                                                .toList(),
                                                crop.getSoilSuitabilities().stream()
                                                                .filter(soil -> soil.getIsSuitable())
                                                                .map(soil -> soil.getSoilType())
                                                                .toList()))
                                .collect(Collectors.toList());

                return ResponseEntity.ok(CropResponse.success(response));
        }

        public ResponseEntity<CropResponse<CropData>> getByCropName(String cropName) {
                Crop crop = cropRepository.findByCropName(cropName)
                                .orElseThrow(() -> new CropNotFoundException("No crop found named: " + cropName));
                CropData cropData = new CropData(
                                crop.getId(),
                                crop.getCropName(),
                                crop.getCategory(),
                                crop.getWeeksToHarvest(),
                                crop.getMinTempCelsius().intValue(),
                                crop.getMaxTempCelsius().intValue(),
                                crop.getWaterNeedMM(),
                                crop.getSeasons().stream()
                                                .map(seasonEntity -> seasonEntity.getName())
                                                .toList(),
                                crop.getSoilSuitabilities().stream()
                                                .filter(soil -> soil.getIsSuitable())
                                                .map(soil -> soil.getSoilType())
                                                .toList());
                return ResponseEntity.ok(CropResponse.success(cropData));
        }

}
