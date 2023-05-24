package org.pedia.starter.redis.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pedia.core.utils.ApplicationUtil;
import org.pedia.starter.redis.constant.RedisConstant;
import org.pedia.starter.redis.enums.PrefixStrategyEnum;
import org.pedia.starter.redis.properties.CommonRedisProperties;
import org.pedia.starter.redis.strategy.RedisKeyStrategy;
import org.pedia.starter.redis.strategy.SystemRedisKeyStrategy;
import org.pedia.starter.redis.strategy.serializer.CustomKeySerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(CommonRedisProperties.class)
public class RedisAutoConfiguration {

    private final CommonRedisProperties commonRedisProperties;

    private final ApplicationUtil applicationUtil;

    public RedisAutoConfiguration(CommonRedisProperties commonRedisProperties, ApplicationUtil applicationUtil) {
        this.commonRedisProperties = commonRedisProperties;
        this.applicationUtil = applicationUtil;
    }

    @Bean(name = RedisConstant.SYSTEM_REDIS_KEY_STRATEGY)
    @ConditionalOnProperty(prefix = "common.redis", name = "auto-prefix", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean(name = RedisConstant.SYSTEM_REDIS_KEY_STRATEGY)
    public SystemRedisKeyStrategy systemRedisKeyStrategy() {
        return new SystemRedisKeyStrategy();
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        Boolean autoPrefix = commonRedisProperties.getAutoPrefix();
        if (autoPrefix != null && autoPrefix) {
            PrefixStrategyEnum strategyEnum = PrefixStrategyEnum.valueOf(commonRedisProperties.getPrefixStrategy());
            // 自动拼接prefix
            return buildRedisTemplate(factory, applicationUtil.getObject(strategyEnum.getBeanName(), strategyEnum.getClazz()));
        }
        return buildRedisTemplate(factory, null);
    }

    private RedisTemplate<String, Object> buildRedisTemplate(RedisConnectionFactory factory, RedisKeyStrategy strategy) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        //自定义redis key的序列化规则，为所有的key添加自定义的前缀
        CustomKeySerializer keySerializer = new CustomKeySerializer(strategy);
        template.setKeySerializer(keySerializer);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setHashKeySerializer(stringRedisSerializer);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

}
