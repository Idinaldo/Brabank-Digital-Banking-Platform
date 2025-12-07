package dev.idinaldo.brabank.auth_service.mapper;

import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.model.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.model.user.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userRequestDtoToUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserResponseDto(User user);
}
