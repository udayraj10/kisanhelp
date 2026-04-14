package dev.kisanhelp.project_kh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kisanhelp.project_kh.dto.response.UserDetailsResponse;
import dev.kisanhelp.project_kh.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDetailsResponse> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        return userService.getUserByName(username);
    }
}
