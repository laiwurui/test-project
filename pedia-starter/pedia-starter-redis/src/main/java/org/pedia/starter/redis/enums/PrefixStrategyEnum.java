package org.pedia.starter.redis.enums;

import org.pedia.starter.redis.constant.RedisConstant;
import org.pedia.starter.redis.strategy.RedisKeyStrategy;
import org.pedia.starter.redis.strategy.SystemRedisKeyStrategy;

public enum PrefixStrategyEnum {

    /**
     * 只拼接系统名称
     */
    APPLICATION_NAME("APPLICATION_NAME", RedisConstant.SYSTEM_REDIS_KEY_STRATEGY, SystemRedisKeyStrategy.class),
    ;
    private final String name;

    private final String beanName;

    private final Class<? extends RedisKeyStrategy> clazz;

    PrefixStrategyEnum(String name, String beanName, Class<? extends RedisKeyStrategy> clazz) {
        this.name = name;
        this.beanName = beanName;
        this.clazz = clazz;
    }

    public Class<? extends RedisKeyStrategy> getClazz() {
        return clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getName() {
        return name;
    }

    public static PrefixStrategyEnum getPrefixBuilderByName(String name) {
        PrefixStrategyEnum builderEnum = null;
        if(name != null) {
            PrefixStrategyEnum[] values = values();
            for (PrefixStrategyEnum value : values) {
                if(name.equals(value.getName())) {
                    builderEnum = value;
                }
            }
        }
        return builderEnum;
    }
}
