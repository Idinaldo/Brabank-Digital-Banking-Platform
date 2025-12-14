package dev.idinaldo.brabank.auth_service.mapper;

import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "password", target = "passwordHash")
    @Mapping(target = "roles", ignore = true)
    User userRequestDtoToUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserResponseDto(User user);
}
