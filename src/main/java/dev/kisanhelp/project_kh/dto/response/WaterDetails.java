package dev.kisanhelp.project_kh.dto.response;

public record WaterDetails(

                String stage,
                Integer frequencyDays,
                Integer perAcre,
                String unit,
                Integer weekStart,
                Integer weekEnd,
                String notes) {

}
