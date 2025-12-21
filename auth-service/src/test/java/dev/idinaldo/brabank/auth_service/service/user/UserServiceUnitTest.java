//package dev.idinaldo.brabank.auth_service.service.user;
//
//import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
//import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
//import dev.idinaldo.brabank.auth_service.mapper.facade.UserMapperFacade;
//import dev.idinaldo.brabank.auth_service.mapper.standard.UserMapper;
//import dev.idinaldo.brabank.auth_service.model.role.Role;
//import dev.idinaldo.brabank.auth_service.model.role.RoleName;
//import dev.idinaldo.brabank.auth_service.model.user.User;
//import dev.idinaldo.brabank.auth_service.repository.UserRepository;
//import dev.idinaldo.brabank.auth_service.service.PasswordService;
//import dev.idinaldo.brabank.auth_service.service.RoleService;
//import dev.idinaldo.brabank.auth_service.service.UserService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.time.LocalDateTime;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceUnitTest {
//
//    @Mock
//    private UserMapperFacade userMapperFacade;
//    @Mock
//    private RoleService roleService;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private PasswordService passwordService;
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    @DisplayName("Should register user with role ROLE_CLIENT successfully when everything is OK")
//    void registerClient() {
//
//        Role clientRole = new Role(UUID.randomUUID(), RoleName.CLIENT, LocalDateTime.now(), LocalDateTime.now());
//        UserRequestDTO request = new UserRequestDTO("didi.moco@gmail.com", "pl@inPW!23");
//        User user = new User(UUID.randomUUID(), "didi.moco@gmail.com", "hash", Set.of(clientRole), LocalDateTime.now(), LocalDateTime.now());
//        UserResponseDTO responseDTO = new UserResponseDTO(user.getId(), user.getEmail(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()), user.getCreatedAt(), user.getUpdatedAt());
//
//        when(userMapperFacade.
//        when(roleService.findRoleByName(RoleName.CLIENT)).thenReturn(clientRole);
//        when(passwordService.hash(request.password())).thenReturn("hash");
//        when(userRepository.save(user)).thenReturn(user);
//        when(userMapper.userToUserResponseDto(user)).thenReturn(responseDTO);
//
//        UserResponseDTO response = userService.registerClient(request);
//
//        verify(userMapper).userRequestDtoToUser(request);
//        verify(roleService).findRoleByName(RoleName.CLIENT);
//        verify(passwordService).hash(request.password());
//        verify(userRepository).save(user);
//        verify(userMapper).userToUserResponseDto(user);
//
//        assertEquals(response, responseDTO);
//        assertTrue(response.roles().stream().anyMatch(role -> role.name().equals(RoleName.CLIENT)));
//    }
//
//}
