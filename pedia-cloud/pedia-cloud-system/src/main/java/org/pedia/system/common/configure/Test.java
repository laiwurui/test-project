package org.pedia.system.common.configure;

import org.pedia.core.utils.ApplicationUtil;
import org.pedia.starter.security.jwe.jwt.JWTEncryptCustomizer;
import org.pedia.starter.security.jwe.jwt.properties.JWTProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {
    @Bean
    public ApplicationUtil applicationUtil() {
        return new ApplicationUtil();
    }


    @Bean
    public JWTEncryptCustomizer pediaOAuth2TokenCustomizer(JWTProperties jwtProperties) {
        return new JWTEncryptCustomizer(jwtProperties);
    }
}
