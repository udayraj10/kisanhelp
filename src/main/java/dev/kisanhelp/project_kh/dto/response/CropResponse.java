package dev.kisanhelp.project_kh.dto.response;

import java.time.LocalDateTime;

public record CropResponse<T>(

        String status,
        LocalDateTime timestamp,
        T data) {

    public static <T> CropResponse<T> success(T data) {
        return new CropResponse<>("success", LocalDateTime.now(), data);
    }
}
