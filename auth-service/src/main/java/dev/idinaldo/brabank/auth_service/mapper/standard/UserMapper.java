package dev.idinaldo.brabank.auth_service.mapper.standard;

import dev.idinaldo.brabank.auth_service.infra.persistence.UserEntity;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {


    // UserRequestDTO -> User
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userSubject", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    User userRequestDtoToUser(UserRequestDTO userRequestDTO);

    // User -> UserResponseDTO
    @Mapping(target = "roles", ignore = true)
    UserResponseDTO userToUserResponseDto(User user, @MappingTarget UserResponseDTO userResponseDTO);

    // User -> UserEntity
    @Mapping(target = "email", ignore = true)
    UserEntity userToUserEntity(User user, @MappingTarget UserEntity userEntity);

    @Mapping(target = "email", ignore = true)
    User userEntityToUser(UserEntity userEntity, @MappingTarget User user);
}
