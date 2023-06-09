package org.pedia.core.annotation;

import org.pedia.core.configure.CommonComponentRegister;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ImportAutoConfiguration(CommonComponentRegister.class)
public @interface EnableCommonConfig {
}
