package dev.idinaldo.brabank.auth_service.dto.role;

import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoleResponseDTO(@NotNull UUID id, @NotNull RoleName name, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt) {
}
