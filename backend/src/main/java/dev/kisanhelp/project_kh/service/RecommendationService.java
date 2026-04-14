package dev.kisanhelp.project_kh.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.kisanhelp.project_kh.dto.request.CropRequest;
import dev.kisanhelp.project_kh.dto.response.FertilizerDetails;
import dev.kisanhelp.project_kh.dto.response.CropResponse;
import dev.kisanhelp.project_kh.dto.response.RecommendationData;
import dev.kisanhelp.project_kh.dto.response.WaterDetails;
import dev.kisanhelp.project_kh.entity.CalendarMonth;
import dev.kisanhelp.project_kh.entity.Crop;
import dev.kisanhelp.project_kh.entity.FertilizerPlan;
import dev.kisanhelp.project_kh.entity.Season;
import dev.kisanhelp.project_kh.entity.SoilSuitability;
import dev.kisanhelp.project_kh.entity.WaterSchedule;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final CropService cropService;
    private final SoilSuitabilityService soilSuitabilityService;
    private final MonthService monthService;

    public ResponseEntity<CropResponse<RecommendationData>> getRecommendation(CropRequest request) {

        Crop crop = cropService.getCrop(request.cropName());
        SoilSuitability soilSuitability = getSoilSuitability(crop.getId(), request.soilType());

        List<String> recommendedCrops = new ArrayList<>();

        if (request.waterSource().equalsIgnoreCase("none")) {
            recommendedCrops = fetchRecommendedCrops(request.soilType());
        }

        RecommendationData data = new RecommendationData(

                crop.getCropName(),
                crop.getCategory(),
                soilSuitability.getSoilType(),
                soilSuitability.getIsSuitable(),
                soilSuitability.getReason(),
                getSuitableSeasons(crop.getSeasons()),
                request.monthToStart(),
                isMonthMatched(crop.getSeasons(), request.monthToStart()),
                getBestMonth(crop.getSeasons()),
                crop.getMinTempCelsius(),
                crop.getMaxTempCelsius(),
                getFertilizerDetails(crop.getFertilizerPlans(), request.acre()),
                getWaterDetails(crop.getWaterSchedules(), request.acre()),
                recommendedCrops);

        return ResponseEntity.ok(CropResponse.success(data));
    }

    private List<FertilizerDetails> getFertilizerDetails(List<FertilizerPlan> fertilizerPlans, Double acre) {

        List<FertilizerDetails> fertilizerDetails = new ArrayList<>();

        for (FertilizerPlan plan : fertilizerPlans) {

            Integer quantity = plan.getQuantityPerAcre().intValue();

            fertilizerDetails.add(new FertilizerDetails(
                    plan.getFertilizerName(),
                    plan.getStage(),
                    quantity,
                    plan.getUnit(),
                    plan.getTimingWeeks(),
                    plan.getInstructions()));
        }

        return fertilizerDetails;
    }

    private List<WaterDetails> getWaterDetails(List<WaterSchedule> waterSchedules, Double acre) {

        List<WaterDetails> waterDetails = new ArrayList<>();

        for (WaterSchedule water : waterSchedules) {

            Integer perAcre = water.getQuantityMM()
                    .multiply(BigDecimal.valueOf(4047)).intValue();

            waterDetails.add(new WaterDetails(
                    water.getStage(),
                    water.getFrequencyDays(),
                    perAcre,
                    "liters",
                    water.getWeekStart(),
                    water.getWeekEnd(),
                    water.getNotes()));

        }

        return waterDetails;
    }

    private List<String> getSuitableSeasons(List<Season> seasons) {

        return seasons.stream()
                .map(Season::getName)
                .toList();
    }

    private SoilSuitability getSoilSuitability(Long cropId, String soilType) {

        return soilSuitabilityService.getSoilSuitability(cropId, soilType);
    }

    private boolean isMonthMatched(List<Season> seasons, String monthName) {
        return monthService.getMonths(seasons).stream()
                .collect(Collectors.groupingBy(CalendarMonth::getSeason))
                .values().stream()
                .anyMatch(months -> months.stream()
                        .limit(2)
                        .anyMatch(m -> m.getMonthName().equalsIgnoreCase(monthName)));
    }

    private List<String> getBestMonth(List<Season> seasons) {
        return monthService.getMonths(seasons).stream()
                .collect(Collectors.groupingBy(CalendarMonth::getSeason))
                .values().stream()
                .flatMap(months -> months.stream()
                        .limit(2)
                        .map(CalendarMonth::getMonthName))
                .toList();
    }

    private List<String> fetchRecommendedCrops(String soilType) {
        return cropService.getRecommendedCrops(soilType);
    }
}
