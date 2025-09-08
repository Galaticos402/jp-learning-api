package com.self.jplearning.config;

import com.self.jplearning.config.property.CognitoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
@EnableConfigurationProperties(CognitoProperties.class)
public class CognitoConfig {
    @Autowired
    private final CognitoProperties properties;
    public CognitoConfig(CognitoProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CognitoIdentityProviderClient cognitoIdentityProviderClient() {
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(properties.getRegion()))
                .build();
    }
}
