package org.pedia.starter.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("common.redis")
public class CommonRedisProperties {

    private Boolean autoPrefix = true;

    private String prefixStrategy = "APPLICATION_NAME";

    public Boolean getAutoPrefix() {
        return autoPrefix;
    }

    public void setAutoPrefix(Boolean autoPrefix) {
        this.autoPrefix = autoPrefix;
    }

    public String getPrefixStrategy() {
        return prefixStrategy;
    }

    public void setPrefixStrategy(String prefixStrategy) {
        this.prefixStrategy = prefixStrategy;
    }

    @Override
    public String toString() {
        return "CommonRedisProperties{" +
                "autoPrefix=" + autoPrefix +
                ", prefixStrategy='" + prefixStrategy + '\'' +
                '}';
    }
}
