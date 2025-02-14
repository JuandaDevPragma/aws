package com.juanojedadev.pragma.aws.infraestructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * The reactive resource server configuration
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebFluxSecurity
public class OAuth2ResourceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String issuer;

    /**
     * The security filter chain to process data
     * @param http The reactive resource security
     * @return security filter chain with settings
     */
    @Bean
    @SuppressWarnings("squid:S4502")
    public SecurityWebFilterChain oauth2CustomResourceFilter(ServerHttpSecurity http){
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeRequests ->
                    authorizeRequests.anyExchange().authenticated())
                .oauth2ResourceServer(rs ->
                        rs.jwt(jwtConfigurer -> jwtConfigurer
                                .jwtDecoder(jwtDecoder())
                                .jwtAuthenticationConverter(new CustomReactiveTokenConverter())))
                .build();

    }

    /**
     * The reactive jwt decoder
     * @return the reactive jwt decoder configured with url introspection
     */
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(this.issuer).build();
    }

    /**
     * The authentication converter to load data in principal
     * @return JwtAuthenticationConverter
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("cognito:groups");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }
}
