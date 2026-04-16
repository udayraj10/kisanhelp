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
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/recommendation")
    public ResponseEntity<CropResponse<RecommendationData>> getRecommendation(
            @RequestBody CropRequest request) {

        return recommendationService.getRecommendation(request);
    }
}
