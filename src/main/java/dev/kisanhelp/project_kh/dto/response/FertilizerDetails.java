package dev.kisanhelp.project_kh.dto.response;

public record FertilizerDetails(

                String fertilizerName,
                String stage,
                Integer quantityPerAcre,
                String unit,
                Integer timingWeeks,
                String instructions) {

}
