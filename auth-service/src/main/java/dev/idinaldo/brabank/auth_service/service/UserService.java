package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.dto.login.LoginRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.login.LoginResponseDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.infra.persistence.UserEntity;
import dev.idinaldo.brabank.auth_service.mapper.facade.UserMapperFacade;
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

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final PasswordService passwordService;
    private final UserMapperFacade userMapperFacade;

    public UserService(UserMapperFacade userMapperFacade, UserRepository userRepository, RoleService roleService, PasswordService passwordService, JwtService jwtService) {
        this.jwtService = jwtService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.userMapperFacade = userMapperFacade;
    }

    // TODO: add a more complete exception handler
    @Transactional
    public UserResponseDTO registerClient(UserRequestDTO userRequestDTO) {
        try {

            User user = userMapperFacade.userRequestDtoToUser(userRequestDTO);
            user.setPasswordHash(passwordService.hash(userRequestDTO.password()));
            Set<Role> roles = Set.of(roleService.findRoleByName(RoleName.CLIENT));
            user.setRoles(roles);
            UserEntity userEntity = userMapperFacade.userToUserEntity(user);
            UserEntity persistedUser = userRepository.save(userEntity);
            userRepository.flush();
            User savedUser = userMapperFacade.userEntityToUser(persistedUser);

            return userMapperFacade.userToUserResponseDto(savedUser);
        } catch (Exception e) {
            // temporary exception "handler"
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }


    public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

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
