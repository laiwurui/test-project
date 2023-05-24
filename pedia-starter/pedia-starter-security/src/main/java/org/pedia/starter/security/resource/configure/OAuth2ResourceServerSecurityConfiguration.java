package org.pedia.starter.security.resource.configure;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * OAuth resource configuration.
 *
 * @author Josh Cummings
 */
@AutoConfiguration
@EnableWebSecurity
@SuppressWarnings("all")
public class OAuth2ResourceServerSecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        AntPathRequestMatcher andRequestMatcher1 = new AntPathRequestMatcher("/user/**", HttpMethod.GET.name());
        AntPathRequestMatcher andRequestMatcher2 = new AntPathRequestMatcher("/user/**", HttpMethod.POST.name());

        http
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(andRequestMatcher1).hasAuthority("SCOPE_message:read")
//                        .requestMatchers(andRequestMatcher2).hasAuthority("SCOPE_message:write")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        // @formatter:on
        return http.build();
    }

//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }

}