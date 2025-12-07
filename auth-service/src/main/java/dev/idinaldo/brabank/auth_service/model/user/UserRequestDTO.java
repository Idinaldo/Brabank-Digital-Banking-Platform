package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.validation.constraints.*;
import java.util.Set;

public record UserRequestDTO(
        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank
        @Pattern(
            regexp = "^[A-ZÀ-Úa-zà-ú]{2,}(?:[ '-][A-ZÀ-Úa-zà-ú]{2,})+$",
            message = "Nome inválido"
        )
        String fullName,

        @NotBlank(message = "Senha é obrigatório")
        @Size(min = 8)
        @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|])[A-Za-z\\d@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|]{8,}$",
            message = "Senha inválida"
        )
        String password,

        @NotBlank(message = "Nome de usuário é obrigatório")
        @Size(min = 2, max = 15)
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

        @NotEmpty Set<Role> roles
) {
}
