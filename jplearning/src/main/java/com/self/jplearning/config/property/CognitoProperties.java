package com.self.jplearning.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.cognito")
@Data
public class CognitoProperties {
    private String userPoolId;
    private String clientId;
    private String clientSecret;
    private String region;
}
