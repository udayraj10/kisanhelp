package dev.kisanhelp.project_kh.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "username is required")
    String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    String email;

    @NotBlank(message = "state is required")
    String state;

    @NotBlank(message = "city is required")
    String city;

    @NotNull(message = "land area is required")
    Double landArea;
    String password;
}
