package dev.idinaldo.brabank.auth_service.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws.kms")
public class KmsProperties {

    private String keyId;
    private String region;
}
