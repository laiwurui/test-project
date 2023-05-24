package org.pedia.system;

import org.pedia.starter.security.authorization.annotation.EnableOAuth2AuthorizationServer;
import org.pedia.starter.security.resource.annotation.EnableOAuth2ResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOAuth2AuthorizationServer
@EnableOAuth2ResourceServer
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
