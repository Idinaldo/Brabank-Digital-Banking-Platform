package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.dto.login.LoginRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.login.LoginResponseDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.mapper.UserMapper;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final PasswordService passwordService;

    public UserService(UserMapper userMapper, UserRepository userRepository, RoleService roleService, PasswordService passwordService, JwtService jwtService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    // TODO: add a more complete exception handler
    @Transactional
    public UserResponseDTO registerClient(UserRequestDTO userRequestDTO) {
        try {
            User user = userMapper.userRequestDtoToUser(userRequestDTO);

            Role clientRole = roleService.findRoleByName(RoleName.CLIENT);

            user.setRoles(Set.of(clientRole));
            user.setPasswordHash(passwordService.hash(userRequestDTO.password()));

            User persistedUser = userRepository.save(user);
            userRepository.flush();

            return userMapper.userToUserResponseDto(persistedUser);
        } catch (Exception e) {
            // temporary exception "handler"
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }


    public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
        LoginResponseDTO response = null;
        if (passwordService.matches(loginRequestDTO.password(), user.getPasswordHash())) {
            String token = jwtService.generateToken(user);
            response = new LoginResponseDTO(token);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "verify your data");
        }
        return response;
    }

}
