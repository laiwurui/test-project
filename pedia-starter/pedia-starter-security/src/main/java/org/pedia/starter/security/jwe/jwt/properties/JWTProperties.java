package org.pedia.starter.security.jwe.jwt.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.jwt")
public class JWTProperties {

    private String encrypt = "disabled";

    private String key;

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "JWTProperties{" +
                "encrypt='" + encrypt + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
