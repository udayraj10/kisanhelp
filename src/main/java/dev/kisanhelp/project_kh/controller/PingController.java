package dev.kisanhelp.project_kh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Health")
public class PingController {

    @GetMapping("/api/ping")
    @Operation(summary = "Check that the API is available")
    public String getPing() {
        return "pong";
    }
}
