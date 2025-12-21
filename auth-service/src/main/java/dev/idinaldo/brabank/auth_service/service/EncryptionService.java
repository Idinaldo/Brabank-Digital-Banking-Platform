package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.infra.config.KmsConfig;
import dev.idinaldo.brabank.auth_service.infra.config.KmsProperties;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DataKeySpec;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class EncryptionService {

    private KmsProperties kmsProperties;
    private KmsConfig kmsConfig;
    @Value("${aws.kms.key-id}")
    private String kmsKeyId;
    private Cipher cipher;
    private SecureRandom SECURE_RANDOM_BYTES_GENERATOR;

    public EncryptionService(KmsConfig kmsConfig, KmsProperties kmsProperties) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.kmsConfig = kmsConfig;
        this.kmsProperties = kmsProperties;
        this.cipher = Cipher.getInstance("AES/GCM/NoPadding");
        this.SECURE_RANDOM_BYTES_GENERATOR = new SecureRandom();
    }

    public GenerateDataKeyResponse requestDataKey() {
        try {
            KmsClient client = kmsConfig.kmsClient();
            return client.generateDataKey(GenerateDataKeyRequest.builder()
                    .keyId(kmsProperties.getKeyId())
                    .keySpec(DataKeySpec.AES_256)
                    .build()
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was a misconfiguration in our servers. Please contact our support.");
        }
    }

    public byte[] generateIv() {
        byte[] iv = new byte[12];
        SECURE_RANDOM_BYTES_GENERATOR.nextBytes(iv);
        return iv;
    }

    public byte[] encrypt(String rawData, byte[] encryptionKey, byte[] iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        SecretKey secretKey = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));

        return cipher.doFinal(rawData.getBytes());
    }

    public String decrypt(byte[] encryptedData, byte[] iv, byte[] encryptedDataKey) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(encryptedDataKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, iv));

        return new String(cipher.doFinal(encryptedData), StandardCharsets.UTF_8);
    }

}
