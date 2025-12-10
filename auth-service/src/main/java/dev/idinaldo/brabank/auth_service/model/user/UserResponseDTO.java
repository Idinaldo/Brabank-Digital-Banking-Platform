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

        @Size(min = 8)
        @NotBlank(message = "Senha é obrigatório")
        @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$\n",
            message = "Senha inválida"
        )
        String password,

        @Size(min = 2, max = 15)
        @NotBlank(message = "Nome de usuário é obrigatório")
        @Pattern(
            regexp = "^[0-9A-Za-z_\\.:\\/]{2,}$",
            message = "Nome de usuário inválido"
        )
        String username,

        @NotEmpty(message = "Role é obrigatório")
        Set<Role> roles,

        @NotNull(message = "createdAt é obrigatório")
        LocalDateTime createdAt,

        @NotNull(message = "updatedAt é obrigatório")
        LocalDateTime updatedAt
){}
