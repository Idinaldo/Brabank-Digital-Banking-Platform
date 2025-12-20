package dev.idinaldo.brabank.auth_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean matches(String raw, String hash) {
        return this.encoder.matches(raw, hash);
    }
    public String hash(String rawPassword) {
        return this.encoder.encode(rawPassword);
    }
}
