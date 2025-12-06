package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        @NotNull UUID id,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "Nome completo é obrigatório")
        @Pattern(
            regexp = "^[A-ZÀ-Úa-zà-ú]{2,}(?:[ '-][A-ZÀ-Úa-zà-ú]{2,})+$",
            message = "Nome inválido"
        )
        String fullName,

        @NotBlank(message = "Senha é obrigatório")
        @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|])[A-Za-z\\d@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|]{8,}$",
            message = "Senha inválida"
        )

        @Size(min = 8)
        @NotBlank(message = "Senha é obrigatório")
        @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|])[A-Za-z\\d@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|]{8,}$",
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

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(
            regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
            message = "CPF inválido"
        )
        String cpf,

        @NotEmpty(message = "Role é obrigatório")
        Set<Role> roles,

        @NotNull(message = "Data de nascimento é obrigatório")
        LocalDate birthDate,

        @NotNull(message = "createdAt é obrigatório")
        LocalDateTime createdAt,

        @NotNull(message = "updatedAt é obrigatório")
        LocalDateTime updatedAt
){}
