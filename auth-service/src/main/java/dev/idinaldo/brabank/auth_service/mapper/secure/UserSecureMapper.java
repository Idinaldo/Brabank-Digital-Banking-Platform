package dev.idinaldo.brabank.auth_service.mapper.secure;

import dev.idinaldo.brabank.auth_service.infra.persistence.UserEntity;
import dev.idinaldo.brabank.auth_service.mapper.facade.RoleMapperFacade;
import dev.idinaldo.brabank.auth_service.model.role.Role;
import dev.idinaldo.brabank.auth_service.model.role.RoleName;
import dev.idinaldo.brabank.auth_service.model.user.User;
import dev.idinaldo.brabank.auth_service.service.EncryptionService;
import dev.idinaldo.brabank.auth_service.service.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Set;

@Component
public class UserSecureMapper {

    private final EncryptionService encryptionService;
    private final RoleMapperFacade roleMapperFacade;
    private final PasswordService passwordService;


    public UserSecureMapper(EncryptionService encryptionService, RoleMapperFacade roleMapperFacade, PasswordService passwordService) {
        this.encryptionService = encryptionService;
        this.roleMapperFacade = roleMapperFacade;
        this.passwordService = passwordService;
    }

    /**
     * @param user
     * @param userEntity
     * @return this method doesn't return anything. It is designed to update an entity (UserEntity).
     */
    public void userToUserEntity(User user, UserEntity userEntity) {

        try {

            byte[] iv = encryptionService.generateIv();
            userEntity.setIv(iv);

            GenerateDataKeyResponse response = encryptionService.requestDataKey();
            byte[] encryptedDataKeyBytes = response.ciphertextBlob().asByteArray();
            userEntity.setDataEncryptionKey(encryptedDataKeyBytes);
            byte[] plaintextDataKeyBytes = response.plaintext().asByteArray();

            byte[] encryptedEmail = encryptionService.encrypt(user.getEmail(), plaintextDataKeyBytes, iv);
            userEntity.setEmail(encryptedEmail);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was a misconfiguration in our servers. Please contact support");
        }
    }

    public Set<RoleName> mapRoleNames(Set<Role> roles) {
        return roleMapperFacade.rolesToRoleNames(roles);
    }

    public void userEntityToUser(UserEntity userEntity, User user) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        byte[] iv = userEntity.getIv();
        byte[] encryptedDataKey = userEntity.getDataEncryptionKey();
        String decryptedEmail = encryptionService.decrypt(userEntity.getEmail(), iv, encryptedDataKey);
        user.setEmail(decryptedEmail);

    }



}
