package dev.idinaldo.brabank.auth_service.dto.user;

import jakarta.validation.constraints.*;

public record UserRequestDTO(
        @NotBlank(message = "E-mail é obrigatório")
        String email,
        
        @NotBlank(message = "Senha é obrigatório")
        String password
) {
}
