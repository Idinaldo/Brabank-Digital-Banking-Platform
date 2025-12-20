package dev.idinaldo.brabank.auth_service.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO (@NotBlank @Email String email, @NotBlank String password) {
}
