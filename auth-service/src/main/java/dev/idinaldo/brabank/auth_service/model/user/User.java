package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

    private UUID id;

    private UUID userSubject;

    @Email(message = "invalid e-mail")
    @NotBlank(message = "e-mail is required")
    private String email;

    @ToString.Exclude
    @NotBlank(message = "password is required")
    private String passwordHash;

    @NotEmpty(message = "roles are required")
    private Set<Role> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public User(UUID id, String email, String passwordHash, Set<Role> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
