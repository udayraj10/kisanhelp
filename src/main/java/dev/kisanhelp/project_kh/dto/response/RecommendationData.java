package dev.kisanhelp.project_kh.dto.response;

import java.math.BigDecimal;
import java.util.List;

import dev.kisanhelp.project_kh.util.SoilType;

public record RecommendationData(

        String cropName,
        String category,
        SoilType soilType,
        boolean isSuitable,
        String soilMsg,
        List<String> bestSeason,
        String monthToStart,
        boolean isMonthSuitable,
        List<String> bestMonthsToStart,
        BigDecimal minTempCelsius,
        BigDecimal maxTempCelsius,
        List<FertilizerDetails> fertilizerDetails,
        List<WaterDetails> waterDetails,
        List<String> recommendedCrops) {

}
