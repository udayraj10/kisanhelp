package dev.kisanhelp.project_kh.dto.request;

public record CropRequest(

        String cropName,
        String soilType,
        double acre,
        String waterSource,
        String monthToStart) {
}
