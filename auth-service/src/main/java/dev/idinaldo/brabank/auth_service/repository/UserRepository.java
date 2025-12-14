package dev.idinaldo.brabank.auth_service.repository;

import dev.idinaldo.brabank.auth_service.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
