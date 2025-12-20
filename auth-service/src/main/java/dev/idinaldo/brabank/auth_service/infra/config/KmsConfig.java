package dev.idinaldo.brabank.auth_service.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest;

@Configuration
public class KmsConfig {

    @Bean
    public KmsClient kmsClient() {
        return KmsClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }
}
