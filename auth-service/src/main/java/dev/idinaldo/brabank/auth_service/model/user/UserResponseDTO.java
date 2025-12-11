package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        @NotNull UUID id,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotEmpty(message = "Role é obrigatório")
        Set<Role> roles,

        @NotNull(message = "createdAt é obrigatório")
        LocalDateTime createdAt,

        @NotNull(message = "updatedAt é obrigatório")
        LocalDateTime updatedAt
){}
