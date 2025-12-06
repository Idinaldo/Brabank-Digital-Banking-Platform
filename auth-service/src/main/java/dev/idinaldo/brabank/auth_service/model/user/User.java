package dev.idinaldo.brabank.auth_service.model.user;

import dev.idinaldo.brabank.auth_service.model.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[A-ZÀ-Úa-zà-ú]{2,}(?:[ '-][A-ZÀ-Úa-zà-ú]{2,})+$", message = "Nome inválido")
    private String fullName;

    @Size(min = 8)
    @ToString.Exclude
    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatório")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|])[A-Za-z\\d@.!#$%¨&*()_\\-+=`´~^\\\\[\\]{}:;?><\\/|]{8,}$",
            message = "Senha inválida"
    )
    private String password;

    @Size(min = 2, max = 15)
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Nome de usuário é obrigatório")
    @Pattern(regexp = "^[0-9A-Za-z_\\.:\\/]{2,}$", message = "Nome de usuário inválido")
    private String username;

    @ToString.Exclude
    @Column(nullable = false, unique = true)
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF inválido")
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
