package org.pedia.starter.redis.util;

public class InvokerKeyGenerator implements RedisKeyGenerator {

    @Override
    public String format(String key) {
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        StackTraceElement invokerStackTrace = stackTrace[2];
        return PREFIX + invokerStackTrace.getClassName() + SEPARATOR + invokerStackTrace.getMethodName() + SEPARATOR + key;
    }

    public String format(Class<?> c, String methodName, String key) {
        return getKeyPrefix(c, methodName) + key;
    }

    public String getKeyPrefix(Class<?> c, String methodName) {
        return PREFIX + c.getName() + SEPARATOR + methodName + SEPARATOR;
    }

}
