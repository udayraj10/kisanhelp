package dev.kisanhelp.project_kh.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kisanhelp.project_kh.dto.response.CropData;
import dev.kisanhelp.project_kh.dto.response.CropResponse;
import dev.kisanhelp.project_kh.service.CropService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/crop")
@Tag(name = "Crops", description = "Search available crops.")
@SecurityRequirement(name = "bearerAuth")
public class CropController {

    private final CropService cropService;

    @GetMapping("/filter")
    @Operation(summary = "Filter crops by soil, season, and category")
    public ResponseEntity<CropResponse<List<CropData>>> filterCrops(
            @RequestParam(required = false) String soilType,
            @RequestParam(required = false) String season,
            @RequestParam(required = false) String category) {

        return cropService.getFilteredCrops(soilType, season, category);
    }

    @GetMapping("/name")
    @Operation(summary = "Find a crop by name")
    public ResponseEntity<CropResponse<CropData>> getByCropName(@RequestParam String cropName) {
        return cropService.getByCropName(cropName);
    }
}
