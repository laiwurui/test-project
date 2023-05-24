package org.pedia.starter.security.authorization.annotation;

import org.pedia.starter.security.authorization.configure.OAuth2AuthorizationServerSecurityConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(OAuth2AuthorizationServerSecurityConfiguration.class)
public @interface EnableOAuth2AuthorizationServer {
}
