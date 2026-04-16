package dev.kisanhelp.project_kh.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponse(

        String status,
        LocalDateTime timestamp,
        String message,
        Map<String, String> error) {

}
