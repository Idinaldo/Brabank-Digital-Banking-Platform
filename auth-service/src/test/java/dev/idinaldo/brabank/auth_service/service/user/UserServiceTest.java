package dev.idinaldo.brabank.auth_service.service.user;

import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.mapper.UserMapper;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.repository.UserRepository;
import dev.idinaldo.brabank.auth_service.service.RoleService;
import dev.idinaldo.brabank.auth_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldRegisterUserWithHashedPasswordAndClientRole() {
        UserRequestDTO dto = new UserRequestDTO("funnytest@mail.com", "pl@inPW!23");
        User user = userMapper.userRequestDtoToUser(dto);
        Role role = roleService.findRoleByName(RoleName.CLIENT);
    }
}
