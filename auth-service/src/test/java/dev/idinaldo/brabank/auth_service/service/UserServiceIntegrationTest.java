package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.dto.role.RoleRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.role.RoleResponseDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Should register user successfully when everything is OK.")
    void shouldRegisterUserWithHashedPasswordAndClientRoleCase1() {
        RoleRequestDTO roleRequestDTO = new RoleRequestDTO(RoleName.CLIENT);
        RoleResponseDTO roleResponseDTO = roleService.addRole(roleRequestDTO);
        UserRequestDTO requestDTO = new UserRequestDTO("didi.moco@gmail.com", "pl@inPW!23");
        UserResponseDTO response = userService.registerClient(requestDTO);

        assertNotNull(response.id());

        User savedUser = userRepository.findById(response.id()).orElseThrow(() -> new IllegalStateException("User not found"));

        assertEquals(response.email(), savedUser.getEmail());
        assertNotEquals(requestDTO.password(), savedUser.getPasswordHash());
        assertTrue(savedUser.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleName.CLIENT)
        );
    }

    @Test
    @DisplayName("Should throw exception when data already exists")
    void registerClient() {

    }


}