package dev.idinaldo.brabank.auth_service.dto.user;

import jakarta.validation.constraints.*;

public record UserRequestDTO(
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,
        
        @NotBlank(message = "Senha é obrigatório")
        @Size(min = 8)
        @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$",
            message = "Senha inválida"
        )
        String password
) {
}
