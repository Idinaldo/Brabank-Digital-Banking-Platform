package dev.idinaldo.brabank.auth_service.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;

@Configuration
public class KmsConfig {

    private KmsProperties kmsProperties;

    public KmsConfig(KmsProperties kmsProperties) {
        this.kmsProperties = kmsProperties;
    }

    @Bean
    public KmsClient kmsClient() {
        return KmsClient.builder()
                .region(Region.of(kmsProperties.getRegion()))
                .build();
    }
}
