package dev.kisanhelp.project_kh.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.kisanhelp.project_kh.entity.SoilSuitability;
import dev.kisanhelp.project_kh.exception.CropNotFoundException;
import dev.kisanhelp.project_kh.repository.SoilSuitabilityRepository;
import dev.kisanhelp.project_kh.util.SoilType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SoilSuitabilityService {

    private final SoilSuitabilityRepository soilSuitabilityRepository;

    public SoilSuitability getSoilSuitability(Long cropId, String soilType) {

        SoilType type = SoilType.fromString(soilType);
        Optional<SoilSuitability> soilSuitability = soilSuitabilityRepository.findByCropIdAndSoilType(cropId, type);
        ;

        if (soilSuitability.isEmpty()) {
            throw new CropNotFoundException("Crop not found");
        }

        return soilSuitability.get();
    }
}
