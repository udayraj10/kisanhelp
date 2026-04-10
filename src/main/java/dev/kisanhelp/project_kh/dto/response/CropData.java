package dev.kisanhelp.project_kh.dto.response;

import java.util.List;

import dev.kisanhelp.project_kh.util.SoilType;

public record CropData(

        Long id,
        String cropName,
        String category,
        Integer weeksToHarvest,
        Integer minTempCelsius,
        Integer maxTempCelsius,
        Integer waterNeedMM,
        List<String> suitableSeasons,
        List<SoilType> suitableSoils) {

}
