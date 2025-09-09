package com.self.jplearning.config;

import com.self.jplearning.repository.ICognitoRepository;
import com.self.jplearning.repository.impl.CognitoRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ICognitoRepository cognitoRepository(){
        return new CognitoRepositoryImpl();
    }
}
