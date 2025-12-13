package dev.idinaldo.brabank.auth_service.dto.role;

import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import jakarta.validation.constraints.NotNull;

public record RoleRequestDTO(@NotNull RoleName name) {
}
