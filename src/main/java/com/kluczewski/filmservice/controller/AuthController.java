package com.kluczewski.filmservice.controller;

import com.kluczewski.filmservice.configuration.security.LoginCredentials;
import com.kluczewski.filmservice.configuration.security.RegistrationCredentials;
import com.kluczewski.filmservice.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public void register(@RequestBody RegistrationCredentials credentials) {
        registrationService.register(credentials);
    }

    @GetMapping("/confirm")
    public void confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {}

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
