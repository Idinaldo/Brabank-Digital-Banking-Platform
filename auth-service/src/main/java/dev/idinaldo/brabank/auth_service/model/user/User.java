package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
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
@Table(name = "app_user")
public class User {

    @Id
    @Column(updatable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email(message = "E-mail inválido")
    @Column(nullable = false, unique = true)
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @ToString.Exclude
    @Column(nullable = false)
    @NotBlank(message = "Nome completo é obrigatório")
    private String fullName;

    @ToString.Exclude
    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatório")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Nome de usuário é obrigatório")
    private String username;

    @ToString.Exclude
    @Column(nullable = false, unique = true)
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotEmpty(message = "Role é obrigatório")
    private Set<Role> roles;

    @Column(nullable = false)
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
