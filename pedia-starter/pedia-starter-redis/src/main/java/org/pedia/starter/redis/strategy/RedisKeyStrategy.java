package org.pedia.starter.redis.strategy;

import org.pedia.starter.redis.configure.RedisAutoConfiguration;
import org.pedia.starter.redis.properties.CommonRedisProperties;

/**
 * redis key前缀扩展，如有需要可以将redis缓存按租户和系统分类
 * 无需要则可禁用
 * @see CommonRedisProperties#getPrefixStrategy()
 * @see RedisAutoConfiguration
 */
public interface RedisKeyStrategy {

    String SEPARATOR = ":";

    /**
     * 统一修改所有的key
     * @param originalKey 业务中自定义的key
     * @return 修改后的key
     */
    String unifiedKey(String originalKey);

}
