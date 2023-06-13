package org.pedia.starter.security;

import org.pedia.starter.security.authorization.token.PediaJwtGrantedAuthoritiesConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

public class SecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository JdbcRegisteredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
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
