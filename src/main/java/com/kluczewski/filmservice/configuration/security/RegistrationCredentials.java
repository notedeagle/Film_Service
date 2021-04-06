package com.kluczewski.filmservice.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationCredentials {
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
}
