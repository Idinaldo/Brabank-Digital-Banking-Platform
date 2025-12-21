package dev.idinaldo.brabank.auth_service.mapper.facade;

import dev.idinaldo.brabank.auth_service.dto.user.UserRequestDTO;
import dev.idinaldo.brabank.auth_service.dto.user.UserResponseDTO;
import dev.idinaldo.brabank.auth_service.infra.persistence.UserEntity;
import dev.idinaldo.brabank.auth_service.mapper.secure.UserSecureMapper;
import dev.idinaldo.brabank.auth_service.mapper.standard.UserMapper;
import dev.idinaldo.brabank.auth_service.model.user.User;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

@Component
public class UserMapperFacade {

    private final UserMapper userMapper;
    private final UserSecureMapper userSecureMapper;

    public UserMapperFacade(UserMapper userMapper, UserSecureMapper userSecureMapper) {
        this.userMapper = userMapper;
        this.userSecureMapper = userSecureMapper;
    }

    public UserResponseDTO userToUserResponseDto(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userMapper.userToUserResponseDto(user, userResponseDTO);
        userResponseDTO.setRoles(userSecureMapper.mapRoleNames(user.getRoles()));
        return userResponseDTO;
    }

    public User userRequestDtoToUser(UserRequestDTO userRequestDTO) {
        return userMapper.userRequestDtoToUser(userRequestDTO);
    }

    public UserEntity userToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userMapper.userToUserEntity(user, userEntity);
        userSecureMapper.userToUserEntity(user, userEntity);
        return userEntity;
    }

    public User userEntityToUser(UserEntity userEntity) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        User user = new User();
        userMapper.userEntityToUser(userEntity, user);
        userSecureMapper.userEntityToUser(userEntity, user);
        return user;
    }
}
