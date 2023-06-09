package org.pedia.starter.security.resource.configure;


import lombok.AllArgsConstructor;
import org.pedia.starter.security.SecurityAutoConfiguration;
import org.pedia.starter.security.authorization.token.PediaJwtGrantedAuthoritiesConverter;
import org.pedia.starter.security.resource.handler.ResourceAccessDeniedHandler;
import org.pedia.starter.security.resource.handler.ResourceAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;

/**
 * OAuth resource auto configuration.
 */
@AutoConfiguration(after = SecurityAutoConfiguration.class)
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressWarnings("all")
public class OAuth2ResourceServerSecurityConfiguration {

    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        // @formatter:off
        http
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin(Customizer.withDefaults())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .oauth2ResourceServer()
                .jwt()
                .decoder(jwtDecoder)
                // 配置解析jwt的方式
                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                .and()
                // 配置从request获取token的方式
                .bearerTokenResolver(bearerTokenResolver())
                .accessDeniedHandler(new ResourceAccessDeniedHandler())
                .authenticationEntryPoint(new ResourceAuthenticationEntryPoint());
        // @formatter:on
        return http.build();
    }

    private BearerTokenResolver bearerTokenResolver() {
        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();

        bearerTokenResolver.setBearerTokenHeaderName(HttpHeaders.AUTHORIZATION);
        bearerTokenResolver.setAllowUriQueryParameter(false);
        bearerTokenResolver.setAllowFormEncodedBodyParameter(false);

        return bearerTokenResolver;
    }

    /**
     * 添加自定义解析jwt权限的JwtGrantedAuthoritiesConverter
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        PediaJwtGrantedAuthoritiesConverter pediaJwtGrantedAuthoritiesConverter = new PediaJwtGrantedAuthoritiesConverter();
        converter.setJwtGrantedAuthoritiesConverter(pediaJwtGrantedAuthoritiesConverter);
        return converter;
    }
}