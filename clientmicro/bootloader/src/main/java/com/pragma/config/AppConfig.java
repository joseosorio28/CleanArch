package com.pragma.config;

import com.pragma.adapters.ClientUseCase;
import com.pragma.mapper.ClientMapper;
import com.pragma.ports.IClientOutputPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Bean
    public WebMvcConfigurer getConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigin)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public ClientUseCase getClientService(
            IClientOutputPort clientOutputPort,
            ClientMapper clientMapper) {
        return new ClientUseCase(clientOutputPort, clientMapper);
    }

}
