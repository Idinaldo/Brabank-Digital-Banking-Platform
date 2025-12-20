package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "app_users")
public class User {

    @Id
    @Column(updatable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(updatable = false, nullable = false)
    private UUID userSubject;

    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @ToString.Exclude
    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatório")
    private String passwordHash;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotEmpty(message = "Role é obrigatório")
    private Set<Role> roles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(UUID id, String email, String passwordHash, Set<Role> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void setUserSubject() {
        this.userSubject = UUID.randomUUID();
    }
}
