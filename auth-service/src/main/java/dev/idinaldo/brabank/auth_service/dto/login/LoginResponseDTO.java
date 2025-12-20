package dev.idinaldo.brabank.auth_service.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String accessToken) {
}