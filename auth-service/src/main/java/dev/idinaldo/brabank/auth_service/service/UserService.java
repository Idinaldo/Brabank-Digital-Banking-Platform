package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.mapper.UserMapper;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.model.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.model.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.repository.RoleRepository;
import dev.idinaldo.brabank.auth_service.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        try {
            Set<Role> roles = Set.of();

            for (RoleName role : userRequestDTO.roles()) {
                roles.add(roleRepository.findByName(role).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "INVALID ROLE")));
            }

            User user = userMapper.userRequestDtoToUser(userRequestDTO);
            user.setRoles(roles);
            User saved_user = userRepository.save(user);
            return userMapper.userToUserResponseDto(saved_user);
        } catch (Exception e) {
            // temporary exception "handler"
            // TODO: add a more complete exception handler
            System.out.println("ERROR: " + e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        }
    }
}
