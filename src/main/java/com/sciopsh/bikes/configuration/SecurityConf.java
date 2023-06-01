package com.sciopsh.bikes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConf {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
                        .antMatchers("/**")
                        .permitAll()
                        .anyRequest()
                        .hasRole("testRole"));
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }
}