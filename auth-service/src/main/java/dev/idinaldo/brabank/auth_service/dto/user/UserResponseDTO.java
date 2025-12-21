package dev.idinaldo.brabank.auth_service.dto.user;

import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDTO {


    @NotNull
    private UUID id;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    String email;

    @NotEmpty(message = "Role é obrigatório")
    Set<RoleName> roles;

    @NotNull(message = "createdAt é obrigatório")
    LocalDateTime createdAt;

    @NotNull(message = "updatedAt é obrigatório")
    LocalDateTime updatedAt;
}
