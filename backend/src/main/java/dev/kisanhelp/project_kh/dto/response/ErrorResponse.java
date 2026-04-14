package dev.kisanhelp.project_kh.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(

        String status,
        String message,
        LocalDateTime timestamp) {

}
