package dev.kisanhelp.project_kh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kisanhelp.project_kh.dto.response.UserDetailsResponse;
import dev.kisanhelp.project_kh.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Users", description = "Manage the authenticated user's profile.")
@SecurityRequirement(name = "bearerAuth")
public class AppUserController {

    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get the current user's profile")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        return userService.getUserByName(username);
    }
}
