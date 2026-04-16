package dev.kisanhelp.project_kh.dto.response;

import java.time.LocalDateTime;

public record UserDetailsResponse(

        String status,
        LocalDateTime timestamp,
        UserDetailsData data) {

}
