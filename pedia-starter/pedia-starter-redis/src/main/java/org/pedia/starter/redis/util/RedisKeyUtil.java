package org.pedia.starter.redis.util;

/**
 * 工具类，获取统一格式的redis key
 */
public class RedisKeyUtil {

    /**
     * 获取统一格式的key
     * @param key 唯一标识
     * @return 格式化后的key
     */
    public static String formatKey(String key) {
        return key;
    }

    public static void main(String[] args) {
        InvokerKeyGenerator invokerKeyGenerator = new InvokerKeyGenerator();
        String format = invokerKeyGenerator.format("12345");
        System.out.println(format);
    }
}
