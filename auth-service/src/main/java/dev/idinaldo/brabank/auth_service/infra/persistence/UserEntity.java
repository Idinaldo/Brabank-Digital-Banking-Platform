package dev.idinaldo.brabank.auth_service.infra.persistence;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "app_users")
public class UserEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(updatable = false, nullable = false)
    @NotEmpty(message = "user subject is required")
    private UUID userSubject;

    @Column(nullable = false)
    @NotEmpty(message = "e-mail is required")
    private byte[] email;

    @Column(nullable = false)
    @NotBlank(message = "password is required")
    private String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotEmpty(message = "Role é obrigatório")
    private Set<Role> rolesSet;

    @Column(nullable = false)
    @NotEmpty(message = "IV is required")
    private byte[] iv;

    @Column(nullable = false)
    @NotEmpty(message = "DEK is required")
    private byte[] dataEncryptionKey;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void setUserSubject() {
        this.userSubject = UUID.randomUUID();
    }

}
