package org.pedia.starter.redis.util;

public interface RedisKeyGenerator {

    String SEPARATOR = ":";

    String PREFIX = "BUSINESS_KEY:";

    String GLOBAL_PREFIX = "BUSINESS_KEY:GLOBAL:";

    String format(String key);

    default String globalKey(String key) {
        return GLOBAL_PREFIX + key;
    }

}
