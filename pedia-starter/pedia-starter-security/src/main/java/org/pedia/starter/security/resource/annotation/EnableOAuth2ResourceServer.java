package org.pedia.starter.security.resource.annotation;

import org.pedia.starter.security.resource.configure.OAuth2ResourceServerSecurityConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(OAuth2ResourceServerSecurityConfiguration.class)
public @interface EnableOAuth2ResourceServer {
}
