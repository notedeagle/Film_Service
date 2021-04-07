package com.kluczewski.filmservice.service;

import com.kluczewski.filmservice.model.entity.ConfirmationToken;
import com.kluczewski.filmservice.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
    public void setConfirmationTime(String token) {
        confirmationTokenRepository.updateConfirmationTime(token, LocalDateTime.now());
    }
}
