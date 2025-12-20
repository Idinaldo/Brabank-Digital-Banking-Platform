package dev.idinaldo.brabank.auth_service.service;

import dev.idinaldo.brabank.auth_service.infra.config.KmsConfig;
import dev.idinaldo.brabank.auth_service.infra.config.KmsProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DataKeySpec;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;

@Service
public class EncryptionService {

    private KmsProperties kmsProperties;
    private KmsConfig kmsConfig;
    @Value("${aws.kms.key-id}")
    private String kmsKeyId;

    public EncryptionService(KmsConfig kmsConfig, KmsProperties kmsProperties) {
        this.kmsConfig = kmsConfig;
        this.kmsProperties = kmsProperties;
    }

    public void encrypt() {
        try {

            KmsClient client = kmsConfig.kmsClient();
            GenerateDataKeyResponse dataEncryptionKey = client.generateDataKey(GenerateDataKeyRequest.builder()
                    .keyId(kmsProperties.getKeyId())
                    .keySpec(DataKeySpec.AES_256)
                    .build()
            );
            System.out.println("RESPONSE: " + dataEncryptionKey);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "There was a misconfiguration error in ours servers. Please contact our support.");
        }
    }
    public void decrypt() {}

}
