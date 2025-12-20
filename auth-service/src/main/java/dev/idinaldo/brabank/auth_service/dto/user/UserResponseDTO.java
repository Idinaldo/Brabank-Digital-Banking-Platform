package dev.idinaldo.brabank.auth_service.dto.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
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
        Set<RoleName> roles,

        @NotNull(message = "createdAt é obrigatório")
        LocalDateTime createdAt,

        @NotNull(message = "updatedAt é obrigatório")
        LocalDateTime updatedAt
){}
