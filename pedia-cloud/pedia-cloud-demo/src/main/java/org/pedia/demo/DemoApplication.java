package org.pedia.demo;

import org.pedia.core.annotation.EnableCommonConfig;
import org.pedia.starter.security.resource.annotation.EnableOAuth2ResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCommonConfig
@EnableOAuth2ResourceServer
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
