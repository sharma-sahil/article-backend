package com.nagarro.training.java.exittest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Normally this bean can be created within {@link WebSecurityConfig} class only,
 * but since the release of spring boot 2.6 version, creating the bean in {@link WebSecurityConfig} will give exception
 * of circular dependency and application will fail to start. So this separate config class is required to break the
 * circular dependency exception.
 */
@Configuration
public class EncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
