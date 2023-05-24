package org.pedia.starter.redis.strategy.serializer;

import org.pedia.starter.redis.strategy.RedisKeyStrategy;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class CustomKeySerializer extends StringRedisSerializer {

    private final RedisKeyStrategy keySerializerStrategy;

    public CustomKeySerializer(RedisKeyStrategy keySerializerStrategy) {
        this.keySerializerStrategy = keySerializerStrategy;
    }

    @Override
    public byte[] serialize(String key) throws SerializationException {
        if(keySerializerStrategy != null) {
            key = keySerializerStrategy.unifiedKey(key);
        }
        return super.serialize(key);
    }

}
