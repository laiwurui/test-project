package org.pedia.starter.redis.strategy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SystemRedisKeyStrategy implements RedisKeyStrategy, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public String unifiedKey(String originalKey) {
        String applicationName = applicationContext.getId();
        return applicationName + SEPARATOR + originalKey;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
