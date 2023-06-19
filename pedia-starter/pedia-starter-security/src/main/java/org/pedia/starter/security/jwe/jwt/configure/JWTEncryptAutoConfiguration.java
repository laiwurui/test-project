package org.pedia.starter.security.jwe.jwt.configure;

import org.pedia.starter.security.jwe.jwt.EncryptJwtDecoderProxy;
import org.pedia.starter.security.jwe.jwt.JWTEncryptCustomizer;
import org.pedia.starter.security.jwe.jwt.properties.JWTProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.lang.reflect.Proxy;

/**
 * 开启jwe之后会对jwt中的信息进行一次加密，防止claim信息对外暴露
 * 若claim中无保密信息，可以不使用
 */
@EnableConfigurationProperties(JWTProperties.class)
@ConditionalOnProperty(prefix = "spring.security.jwt", name = "encrypt", havingValue = "enabled")
public class JWTEncryptAutoConfiguration implements BeanPostProcessor {

    private final JWTProperties jwtProperties;

    public JWTEncryptAutoConfiguration(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    //    @Bean
//    @Primary
    public JWTEncryptCustomizer jweTokenCustomizer(JWTProperties jwtProperties) {
        return new JWTEncryptCustomizer(jwtProperties);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof JwtDecoder) {
            ClassLoader classLoader = JwtDecoder.class.getClassLoader();
            return Proxy.newProxyInstance(
                    classLoader,
                    bean.getClass().getInterfaces(),
                    new EncryptJwtDecoderProxy(jwtProperties, (JwtDecoder) bean));
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
