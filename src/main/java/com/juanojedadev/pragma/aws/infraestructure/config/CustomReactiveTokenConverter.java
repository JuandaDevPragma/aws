package com.juanojedadev.pragma.aws.infraestructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

/**
 * The reactive resource server configuration
 *
 * @author juan.ojeda@pragma.com.co
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class CustomReactiveTokenConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<AbstractAuthenticationToken> convert(@NonNull Jwt source) {
        return getAuthorities(source)
                .collectList()
                .map(grantedAuthorities ->
                        new JwtAuthenticationToken(source, grantedAuthorities, extractUsername(source)));
    }

    /**
     * Method to recover user roles by scope field in token introspection response
     * @param jwt the jwt token stored
     * @return list of granted authorities as flux type
     */
    private Flux<GrantedAuthority> getAuthorities(Jwt jwt) {
        return Flux.fromIterable(jwt.getClaimAsStringList("scope"))
                .map(SimpleGrantedAuthority::new);
    }

    /**
     * Method to extract the session username, could be used to recover data from token
     * @param jwt the jwt stored from introspection
     * @return the user from jwk introspection
     */
    private String extractUsername(Jwt jwt) {
        return jwt.getClaimAsString("username");
    }
}
