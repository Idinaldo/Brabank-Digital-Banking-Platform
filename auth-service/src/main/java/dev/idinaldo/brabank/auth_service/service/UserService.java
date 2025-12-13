package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.mapper.UserMapper;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.repository.RoleRepository;
import dev.idinaldo.brabank.auth_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserMapper userMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // TODO: add a more complete exception handler
    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        try {
            User user = userMapper.userRequestDtoToUser(userRequestDTO);
            Role client_role = roleRepository.findByName(RoleName.CLIENT).orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ROLE_CLIENT NOT FOUND"));
            user.setRoles(Set.of(client_role));
            User saved_user = userRepository.save(user);
            return userMapper.userToUserResponseDto(saved_user);
        } catch (Exception e) {
            // temporary exception "handler"
            logger.info("ERROR: " + e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }
}
