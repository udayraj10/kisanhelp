package dev.kisanhelp.project_kh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kisanhelp.project_kh.dto.request.CropRequest;
import dev.kisanhelp.project_kh.dto.response.RecommendationData;
import dev.kisanhelp.project_kh.dto.response.CropResponse;
import dev.kisanhelp.project_kh.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Recommendations", description = "Generate crop recommendations.")
@SecurityRequirement(name = "bearerAuth")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/recommendation")
    @Operation(summary = "Get a crop recommendation")
    public ResponseEntity<CropResponse<RecommendationData>> getRecommendation(
            @RequestBody CropRequest request) {

        return recommendationService.getRecommendation(request);
    }
}
